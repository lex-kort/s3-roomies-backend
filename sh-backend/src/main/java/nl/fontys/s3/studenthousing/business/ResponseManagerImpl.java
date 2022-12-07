package nl.fontys.s3.studenthousing.business;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseManager;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseManagerImpl implements ResponseManager {
    private final ResponseRepository responseRepository;
}
