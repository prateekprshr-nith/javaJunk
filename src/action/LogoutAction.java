package action;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;


/**
 * Class LogoutAction, this is the action class
 * for loggin out a valid user
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class LogoutAction extends Action {
    public ActionRedirect execute(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            session.removeAttribute("logged");
            session.removeAttribute("username");
            session.invalidate();
        }

        return new ActionRedirect(mapping.findForward("taskView"));
    }
}
