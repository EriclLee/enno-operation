package enno.operation.core.Operation;

import enno.operation.core.common.LogUtil;
import enno.operation.core.common.pageDivisionQueryUtil;
import enno.operation.core.model.*;
import enno.operation.core.model.Enum;
import enno.operation.dal.CloseableSession;
import enno.operation.dal.EventsourceSubscriberMapEntity;
import enno.operation.dal.SubscriberEntity;
import enno.operation.dal.hibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sabermai on 2015/11/11.
 */
public class subscriberOperation {
    private eventSourceOperation esOp = null;
    private Session session = null;

    //region 对外提供的Public方法
    //获取订阅者列表，分页，PageIndex：当前页码，PageSize：每页记录条数
    public PageDivisionQueryResultModel<SubscriberModel> getPageDivisonSubscriberList(int pageIndex) throws Exception {
        PageDivisionQueryResultModel<SubscriberModel> result = new PageDivisionQueryResultModel<SubscriberModel>();

        try {
            PageDivisionQueryResultModel<SubscriberEntity> entityList = new PageDivisionQueryResultModel<SubscriberEntity>();
            entityList = getPageDivisonSubscriberEntityList(pageIndex);
            //result.setPageSize(pageSize);
            result.setRecordCount(entityList.getRecordCount());
            result.setCurrentPageIndex(pageIndex);
            result.setQueryResult(ConvertSubscriberEntityList2ModelList(entityList.getQueryResult()));
            result.setPageCount();
            return result;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    //获取订阅者详情 Done
    public SubscriberModel getSubscriberById(int SubscriberId) throws Exception {
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            Transaction tx = session.beginTransaction();
            esOp = new eventSourceOperation();
            SubscriberEntity subEntity = getSubscriberEntityById(SubscriberId);
            tx.commit();
            SubscriberModel suber = ConvertSubscriberEntity2Model(subEntity);
            suber.setEventsourceList(esOp.GetEventsourcesBySubscriberId(SubscriberId));
            return suber;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    //通过EventSource的id获取Subscriber列表
    public List<SubscriberModel> getSubscriberListByEventSourceId(int EventSourceId) throws Exception {
        List<SubscriberModel> suberList = new ArrayList<SubscriberModel>();
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            List<SubscriberEntity> suberEntityList = getSubscriberEntityListByEventSourceId(EventSourceId);
            if (suberEntityList != null) {
                for (SubscriberEntity sub : suberEntityList) {
                    SubscriberModel suber = new SubscriberModel();
                    suber = ConvertSubscriberEntity2Model(sub);
                    //suber.setEventsourceList(esOp.GetEventsourcesBySubscriberId(sub.getId()));
                    suberList.add(suber);
                }
            }
            return suberList;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    //获取未订阅指定Eventsource的Subscriber列表
    public List<SubscriberModel> getUnSubscribeListByEventSourceId(int EventSourceId) throws Exception {
        List<SubscriberModel> suberList = new ArrayList<SubscriberModel>();
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            List<SubscriberEntity> AllSuberEntites = getAllSubscriberEntities();
            List<SubscriberEntity> suberEntityList = getSubscriberEntityListByEventSourceId(EventSourceId);
            AllSuberEntites.removeAll(suberEntityList);

            for (SubscriberEntity sub : AllSuberEntites) {
                SubscriberModel suber = new SubscriberModel();
                suber = ConvertSubscriberEntity2Model(sub);
                //suber.setEventsourceList(esOp.GetEventsourcesBySubscriberId(sub.getId()));
                suberList.add(suber);
            }

            return suberList;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    public void DeleteSubscriber(int SubscriberId) throws Exception {
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            Transaction tx = session.beginTransaction();
            SubscriberEntity suber = getSubscriberEntityById(SubscriberId);
            if (suber.getStatus() == Enum.State.Offline.ordinal()) {
                Query q = session.createQuery("from EventsourceSubscriberMapEntity m where m.subscriber.id = :SubscriberId");
                q.setParameter("SubscriberId", SubscriberId);
                List<EventsourceSubscriberMapEntity> MapEntities = q.list();
                for (EventsourceSubscriberMapEntity MapEntity : MapEntities) {
                    session.delete(MapEntity);
                }

                q = session.createQuery("update SubscriberEntity e set e.dataStatus = :dataStatus, e.status = :status where e.id = :SubscriberId");
                q.setParameter("dataStatus", Enum.State.Offline.ordinal());
                q.setParameter("status", Enum.validity.invalid.ordinal());
                q.setParameter("SubscriberId", SubscriberId);
                q.executeUpdate();

                tx.commit();
            }
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    public void UpdateSubscriber(SubscriberModel Subscriber) throws Exception {
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from SubscriberEntity sub where sub.id = :SubscriberId").setParameter("SubscriberId", Subscriber.getId());
            SubscriberEntity sub = (SubscriberEntity) q.uniqueResult();
            sub.setName(Subscriber.getName());
            sub.setComments(Subscriber.getComments());
            sub.setAddress(Subscriber.getAddress());
            tx.commit();
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    public SubscriberEntity getSubscriberEntityById(int SubscriberId) throws Exception {
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            Query q = session.createQuery("from SubscriberEntity sub where sub.id = :SubscriberId");
            q.setParameter("SubscriberId", SubscriberId);
            SubscriberEntity subEntity = (SubscriberEntity) q.uniqueResult();
            return subEntity;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    public void OfflineSubscriber(int subscriberId) throws Exception {
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())) {
            session = closeableSession.getSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("from EventsourceSubscriberMapEntity m where m.subscriber.id = :SubscriberId");
            q.setParameter("SubscriberId", subscriberId);
            List<EventsourceSubscriberMapEntity> MapEntities = q.list();
            if (MapEntities == null || MapEntities.size() == 0) {
                SubscriberEntity suber = getSubscriberEntityById(subscriberId);
                suber.setStatus(Enum.State.Offline.ordinal());
            }
            tx.commit();
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }
    //endregion

    //region 私有方法
    private List<SubscriberEntity> getSubscriberEntityListByEventSourceId(int EventSourceId) throws Exception {
        List<SubscriberEntity> suberEntityList = new ArrayList<SubscriberEntity>();

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select sub from EventsourceSubscriberMapEntity m join m.eventsource es join m.subscriber sub where sub.dataStatus = 1 and es.id = :EventsourceId");
            q.setParameter("EventsourceId", EventSourceId);
            suberEntityList = (List<SubscriberEntity>) q.list();
            tx.commit();
            return suberEntityList;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    private List<SubscriberEntity> getAllSubscriberEntities() throws Exception {
        List<SubscriberEntity> suberEntityList = new ArrayList<SubscriberEntity>();

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from SubscriberEntity sub where sub.dataStatus = :dataStatus");
            q.setParameter("dataStatus", Enum.validity.valid.ordinal());
            suberEntityList = (List<SubscriberEntity>) q.list();
            tx.commit();
            return suberEntityList;
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }

    //Done
    public SubscriberModel ConvertSubscriberEntity2Model(SubscriberEntity suberEntity) throws Exception {
        SubscriberModel suber = new SubscriberModel();

        suber.setId(suberEntity.getId());
        suber.setUpdateTime(suberEntity.getUpdateTime());
        suber.setAddress(suberEntity.getAddress());
        suber.setComments(suberEntity.getComments());
        suber.setCreateTime(suberEntity.getCreateTime());
        suber.setName(suberEntity.getName());
        suber.setState(Enum.State.values()[suberEntity.getStatus()]);

        return suber;
    }

    private List<SubscriberModel> ConvertSubscriberEntityList2ModelList(List<SubscriberEntity> entityList) throws Exception {
        esOp = new eventSourceOperation();
        List<SubscriberModel> result = new ArrayList<SubscriberModel>();
        for (SubscriberEntity entity : entityList) {
            SubscriberModel suber = new SubscriberModel();
            suber = ConvertSubscriberEntity2Model(entity);
            suber.setEventsourceList(esOp.GetEventsourcesBySubscriberId(entity.getId()));
            result.add(suber);
        }
        return result;
    }

    //获取Subscriber列表，分页
    private PageDivisionQueryResultModel<SubscriberEntity> getPageDivisonSubscriberEntityList(int pageIndex) throws Exception {
        pageDivisionQueryUtil<SubscriberEntity> util = new pageDivisionQueryUtil();

        try {
            String queryHQL = "from SubscriberEntity se where se.dataStatus = 1 order by se.id asc";
            String countHQL = "select count(*) from SubscriberEntity se where se.status = 1 and se.dataStatus = 1";
            return util.excutePageDivisionQuery(pageIndex, queryHQL, countHQL);
        } catch (Exception ex) {
            LogUtil.SaveLog(subscriberOperation.class.getName(), ex);
            throw ex;
        }
    }
    //endregion

}
