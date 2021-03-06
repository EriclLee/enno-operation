package enno.operation.core.server;

import enno.operation.zkl.ZKAgentUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.logging.Logger;

/**
 * Created by EriclLee on 15/11/29.
 */
public class Operation_Server extends HttpServlet {

    /**
     * Serial version UUID
     */
    private static final long serialVersionUID = -8696135593175193509L;

    /**
     * Static logger instance
     */
    //private static Logger LOGGER = Logger.getLogger(Operation_Server.class);
    private static final ZK_Loader zk_Loader = new ZK_Loader();
    private static final ZKAgent_Loader zka_Loader = new ZKAgent_Loader();

    //private static final
    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        try {
            super.init();
            zk_Loader.start();
            zka_Loader.loadZKAgent();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
