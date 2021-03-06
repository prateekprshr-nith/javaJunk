package action;

import model.Task;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

/**
 * Class TaskViewAction, this is the action class
 * for addition of new tasks
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class TaskModifyAction extends Action {

    private void addTask(String description) {
        Task task = new Task(description);
        task.save();
    }


    private void deleteTask(int id) {
        Task task = Task.find(id);
        task.delete();
    }


    public ActionRedirect execute(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestType = request.getParameter("requestType");

        if (requestType.equals("PUT")) {
            String description = request.getParameter("description");
            addTask(description);
        } else if (requestType.equals("DELETE")) {
            String id = request.getParameter("id");
            deleteTask(Integer.parseInt(id));
        } else {
            System.out.println("Invalid request type: " + requestType);
        }

        return new ActionRedirect(mapping.findForward("taskView"));
    }
}
