package enno.operation.core.zkevent;

import enno.operation.zkl.ZKListener;

import java.util.Map;

/**
 * Created by v-zoli on 2015/11/10.
 */
public class subscriberChildrenChange implements ZKListener {
    public void process(Map<String, String> nodes) {
        if(nodes.isEmpty())
        {
            //TODO: �����ж�����״̬��Ϊ���ߣ�DB
            //TODO����������EventSource״̬��Ϊ���У�DB��ZK��
        }
        else
        {
            //TODO�������ݿ����ݱȽ��ҵ����и����ߵĶ�����
            //TODO: ����Щ������״̬��Ϊ���ߣ�DB
            //TODO��������Щ�����߶��ĵ�����EventSource״̬��Ϊ���У�DB��ZK��

            //TODO������nodes��Ӧ�����ߵ�״̬Ϊ���ߣ�����������ߵ���ע�ᵽ���ݿ�
        }
    }
}
