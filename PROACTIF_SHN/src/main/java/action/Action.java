package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Liam
 */
public abstract class Action {
    
    public abstract boolean executer(HttpServletRequest request, HttpSession session);
}
