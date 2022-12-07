package nl.fontys.s3.studenthousing.core.interfaces;

import org.apache.coyote.Response;

import java.util.List;

public interface OrderStrategy {
    List<Response> determineOrder(List<Response> input);
}
