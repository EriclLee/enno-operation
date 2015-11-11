package enno.operation.core.zkevent;

import enno.operation.dal.EventsourceSubscriberMapEntity;
import enno.operation.dal.SubscriberEntity;
import enno.operation.dal.hibernateHelper;
import enno.operation.zkl.ZKListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by v-zoli on 2015/11/10.
 */
public class subscriberChildrenChange implements ZKListener {
    public void process(Map<String, String> nodes) {
        Session session = null;
        try {
            session = hibernateHelper.getSessionFactory().openSession();
            Transaction transaction = null;
            if (nodes.isEmpty()) {
                try {
                    transaction = session.beginTransaction();
                    List<SubscriberEntity> subscriberEntityList = session.createQuery("from SubscriberEntity fetch all properties").list();
                    for (SubscriberEntity subscriberEntity : subscriberEntityList) {
                        //�����ж�����״̬��Ϊ���ߣ�DB
                        subscriberEntity.setStatus(1);
                        //��������EventSource״̬��Ϊ���У�DB��ZK��
                        List<EventsourceSubscriberMapEntity> eventsourceSubscriberMapEntities
                                = session.createQuery("from EventsourceSubscriberMapEntity fetch all properties where subscriberId = " + subscriberEntity.getId()).list();
                        for (EventsourceSubscriberMapEntity eventsourceSubscriberMapEntity : eventsourceSubscriberMapEntities) {
                            session.delete(eventsourceSubscriberMapEntity);
                        }
                    }
                    transaction.commit();
                }catch (Exception ex)
                {
                    if(transaction!=null){
                        transaction.rollback();
                    }
                    ex.printStackTrace();

                }
            } else {
//
//                for()
//                transaction = session.beginTransaction();
//                //TODO�������ݿ����ݱȽ��ҵ����и����ߵĶ�����
//                List<SubscriberEntity> subscriberEntityList = session.createQuery("from SubscriberEntity fetch all properties where status = 1" ).list();
//                for(SubscriberEntity subscriberEntity : subscriberEntityList)
//                {
//                   for()
//                }
                //TODO: ����Щ������״̬��Ϊ���ߣ�DB
                //TODO��������Щ�����߶��ĵ�����EventSource״̬��Ϊ���У�DB��ZK��
                //TODO������nodes��Ӧ�����ߵ�״̬Ϊ���ߣ�����������ߵ���ע�ᵽ���ݿ�
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(session!=null)
            {
                session.close();
            }
        }
    }
}
