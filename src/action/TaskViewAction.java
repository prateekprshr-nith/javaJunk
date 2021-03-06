package action;

import model.Task;
import java.util.ArrayList;
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

        // Get the list of all tasks and add the list in request
        ArrayList<Task> tasks = Task.all();
        request.setAttribute("tasks", tasks);

        return mapping.findForward("taskView");
    }
}
