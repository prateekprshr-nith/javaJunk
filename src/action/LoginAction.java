package action;

import model.User;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;


/**
 * Class LoginAction, this is the action class
 * for authenticating a valid user
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class LoginAction extends Action {
    public ActionRedirect execute(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            return new ActionRedirect(mapping.findForward("taskView"));
        } else {
            String username = (String) request.getParameter("username");
            String password = (String) request.getParameter("password");

            User user = User.find(username);

            if (user == null || !password.equals(user.getPassword())) {
                session.setAttribute("error", "The credentials are invalid!!!");

                return new ActionRedirect(mapping.findForward("taskView"));
            } else {
                session.setAttribute("logged", "");
                session.setAttribute("user", user);
                session.removeAttribute("error");

                return new ActionRedirect(mapping.findForward("taskView"));
            }
        }
    }
}
