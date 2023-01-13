package nl.fontys.s3.studenthousing.business.strategies;

import nl.fontys.s3.studenthousing.core.exceptions.CustomException;
import nl.fontys.s3.studenthousing.core.exceptions.EmptyInputException;
import nl.fontys.s3.studenthousing.core.interfaces.OrderStrategy;
import nl.fontys.s3.studenthousing.domain.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueueStrategy extends BaseStrategy implements OrderStrategy {
    @Override
    public List<Response> determineResponseOrder(List<Response> input) {
        if(input == null) throw new EmptyInputException();
        List<Response> output = new ArrayList<>(filterRoles(input));
        output.sort(Comparator.comparing(Response::getResponseDate));
        return output;
    }
}
