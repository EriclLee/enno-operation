package enno.operation.core.common;

import enno.operation.core.model.PageDivisionQueryResultModel;
import enno.operation.dal.CloseableSession;
import enno.operation.dal.hibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by sabermai on 2015/11/11.
 */
public class pageDivisionQueryUtil<T> {
    public pageDivisionQueryUtil() {
    }

    private Session session = null;

    public PageDivisionQueryResultModel<T> excutePageDivisionQuery(int currentPageIndex, String queryHqlStatement, String countHqlStatement) throws Exception {
        PageDivisionQueryResultModel<T> resultModel = new PageDivisionQueryResultModel();
        List<T> result = null;
        try (CloseableSession closeableSession = new CloseableSession(hibernateUtil.getSessionFactory().openSession())){
            session = closeableSession.getSession();
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery(queryHqlStatement);
            q.setFirstResult((currentPageIndex - 1) * resultModel.getPageSize());
            q.setMaxResults(resultModel.getPageSize());
            result = (List<T>) q.list();

            q = session.createQuery(countHqlStatement);
            long count = (Long) q.uniqueResult();
            tx.commit();

            resultModel.setCurrentPageIndex(currentPageIndex);
            //resultModel.setPageSize(pageSize);
            resultModel.setRecordCount((int) count);
            resultModel.setQueryResult(result);
            resultModel.setPageCount();
            return resultModel;
        } catch (Exception ex) {
            LogUtil.SaveLog(pageDivisionQueryUtil.class.getName(), ex);
            throw ex;
        }
    }
}
