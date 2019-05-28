package tm.server.webservice;

import tm.server.api.webservice.UserWebService;
import tm.server.entity.User;
import tm.server.utils.CypherUtil;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebService(endpointInterface = "tm.server.api.webservice.UserWebService")
public class UserWebServiceBean implements UserWebService {

    @Override
    public Collection<User> getNew() {
        final User userOne = new User();
        userOne.setLogin("gael");
        userOne.setPasswordHash(CypherUtil.getMd5("knight"));
        userOne.setRole(User.Role.ADMIN);

        final User userTwo = new User();
        userTwo.setLogin("thy");
        userTwo.setPasswordHash(CypherUtil.getMd5("thine"));
        userTwo.setRole(User.Role.ADMIN);

        final Collection<User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);

        return list;
    }
}
