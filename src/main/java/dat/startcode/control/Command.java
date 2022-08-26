package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("register", new Register());
        commands.put("startup", new StartUp());
        commands.put("materialoverview", new MaterialOverview());
        commands.put("materialgetbyid", new MaterialGetById());
        commands.put("materialmodify", new MaterialModify());
        commands.put("deletematerial", new DeleteMaterial());
        commands.put("creatematerial", new CreateMaterial());
        commands.put("quickbuild", new Quickbuild());
        commands.put("requestsent", new RequestSent());
        commands.put("requestoverview", new RequestOverview());
        commands.put("requestview", new RequestView());
        commands.put("requestdeny", new RequestDeny());
        commands.put("requestapprove", new RequestApprove());
        commands.put("orderoverview", new OrderOverview());
        commands.put("orderview", new OrderView());
        commands.put("customermodify", new CustomerModify());
        commands.put("customerprofile", new CustomerProfile());
        commands.put("customerrequestoverview", new CustomerRequestOverview());
        commands.put("customerindex", new CustomerIndex());
        commands.put("customermodifyredirect", new CustomerModifyRedirect());
        commands.put("customerorderview", new CustomerOrderView());
        commands.put("materialcreate", new MaterialCreate());

    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());   // unknowncommand er default.
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws DatabaseException, SQLException;

}
