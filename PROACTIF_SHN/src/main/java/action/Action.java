package action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Liam
 */
public abstract class Action {
    
    public abstract boolean executer(HttpServletRequest request);
}
