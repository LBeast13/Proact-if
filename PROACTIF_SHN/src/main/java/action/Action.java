package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Liam
 */
public abstract class Action {
    
    public abstract boolean executer(HttpServletRequest request, HttpSession session);
    
    protected String formattageDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        
        return dateFormat.format(date);
    }
}
