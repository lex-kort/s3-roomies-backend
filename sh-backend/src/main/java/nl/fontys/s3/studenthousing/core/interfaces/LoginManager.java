package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.Login;

public interface LoginManager {
    public String authenticate(Login login);
}
