package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.Response;

import java.util.List;

public interface OrderStrategy {
    List<Response> determineResponseOrder(List<Response> input);
}
