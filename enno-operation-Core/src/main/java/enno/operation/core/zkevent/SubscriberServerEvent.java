package enno.operation.core.zkevent;

import enno.operation.ZKListener.SubscriberClusterListener;
import enno.operation.core.DataConversion.DataConversionFactory;
import enno.operation.core.DataConversion.IDataConversion;
import enno.operation.core.DataConversion.SubscriberConversion;
import enno.operation.core.model.SubscriberModel;
import enno.operation.dal.EventsourceSubscriberMapEntity;
import enno.operation.dal.SubscriberEntity;
import enno.operation.dal.hibernateHelper;
import enno.operation.zkmodel.SubscriberData;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by v-zoli on 2015/11/10.
 */
public class SubscriberServerEvent implements SubscriberClusterListener {
    public void process(List<SubscriberData> subscriberDataList) {
//        Session session = null;
//        try {
//            session = hibernateHelper.getSessionFactory().openSession();
//            Transaction transaction = null;
//            if (nodes.isEmpty()) {
//                try {
//                    transaction = session.beginTransaction();
//                    List<SubscriberEntity> subscriberEntityList = session.createQuery("from SubscriberEntity fetch all properties").list();
//                    for (SubscriberEntity subscriberEntity : subscriberEntityList) {
//                        //�����ж�����״̬��Ϊ���ߣ�DB
//                        subscriberEntity.setStatus(1);
//                        //��������EventSource״̬��Ϊ���У�DB��ZK��
//                        List<EventsourceSubscriberMapEntity> eventsourceSubscriberMapEntities
//                                = session.createQuery("from EventsourceSubscriberMapEntity fetch all properties where subscriberId = " + subscriberEntity.getId()).list();
//                        for (EventsourceSubscriberMapEntity eventsourceSubscriberMapEntity : eventsourceSubscriberMapEntities) {
//                            session.delete(eventsourceSubscriberMapEntity);
//                        }
//                    }
//                    transaction.commit();
//                }catch (Exception ex)
//                {
//                    if(transaction!=null){
//                        transaction.rollback();
//                    }
//                    ex.printStackTrace();
//
//                }
//            } else {
//
//                //for (Map.Entry<String, String> node : nodes.entrySet()) {
//                //TODO�������ݿ����ݱȽ��ҵ����и����ߵĶ�����
//                List<SubscriberEntity> subscriberEntityList = session.createQuery("from SubscriberEntity fetch all properties where status = 1").list();
//                for (SubscriberEntity subscriberEntity : subscriberEntityList) {
//                    boolean exist=false;
//                    for (Map.Entry<String, String> node : nodes.entrySet())
//                    {
//                        DataConversionFactory<SubscriberModel> dataConversionFactory = new DataConversionFactory<SubscriberModel>();
//                        IDataConversion<SubscriberModel> dataConversion = dataConversionFactory.createDataConversion(SubscriberConversion.class);
//                        SubscriberModel subscriberModel = dataConversion.Decode(node.getKey(), node.getValue());
//                        if(subscriberModel.getId() == subscriberEntity.getId()){
//                            exist=true;
//                            subscriberEntity.setStatus(1);
//                            break;
//                        }
//                    }
//                    if(!exist){
//
//                    }
//                }
//
//                //TODO: ����Щ������״̬��Ϊ���ߣ�DB
//
//                //TODO��������Щ�����߶��ĵ�����EventSource״̬��Ϊ���У�DB��ZK��
//                //}
//                //TODO������nodes��Ӧ�����ߵ�״̬Ϊ���ߣ�����������ߵ���ע�ᵽ���ݿ�
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            if(session!=null)
//            {
//                session.close();
//            }
//        }
    }
}
