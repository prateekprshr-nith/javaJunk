package action;

import model.Task;
import model.User;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Class TaskViewAction, this is the action class
 * for displaying the list of current tasks
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class TaskViewAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            User user = (User) session.getAttribute("user");

            // Get the list of all tasks associated with this user
            ArrayList<Task> tasks = user.tasks();
            request.setAttribute("tasks", tasks);
        }

        return mapping.findForward("taskView");
    }
}
