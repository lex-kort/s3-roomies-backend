package nl.fontys.s3.studenthousing.business.strategies;

import nl.fontys.s3.studenthousing.core.interfaces.OrderStrategy;
import org.apache.coyote.Response;

import java.util.List;

public class QueueStrategy implements OrderStrategy {
    @Override
    public List<Response> determineOrder(List<Response> input) {
        return null;
    }
}
