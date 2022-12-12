package nl.fontys.s3.studenthousing.persistence;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseJPA;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResponseRepositoryImpl implements ResponseRepository {
    private final ResponseJPA responseJPA;
}
