package nl.fontys.s3.studenthousing.business.strategies;

import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.domain.Response;

import java.util.Collections;
import java.util.List;

public abstract class BaseStrategy{
    protected List<Response> filterRoles(List<Response> input){
        if(input == null){
            return Collections.emptyList();
        }
        return input.stream()
                .filter(response -> response.getUser().getUserRole() == UserRoles.STUDENT)
                .toList();
    }
}
