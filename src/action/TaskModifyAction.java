package action;

import model.Task;
import model.User;
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
    public ActionRedirect execute(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");

        String requestType = request.getParameter("requestType");

        if (requestType.equals("PUT")) {
            String description = request.getParameter("description");
            addTask(description, user.getUsername());
        } else if (requestType.equals("DELETE")) {
            String id = request.getParameter("id");
            deleteTask(Integer.parseInt(id));
        } else {
            System.out.println("Invalid request type: " + requestType);
        }

        return new ActionRedirect(mapping.findForward("taskView"));
    }


    /**
     * This method adds a new task for the logged in user
     *
     * @param description The descriptio of the task
     * @param username The username of the logged in user
     */
    private void addTask(String description, String username) {
        Task task = new Task(description, username);
        task.save();
    }


    /**
     * This method deletes a task for the logged in user
     *
     * @param id The id of the task
     */
    private void deleteTask(int id) {
        Task task = Task.find(id);
        task.delete();
    }
}
