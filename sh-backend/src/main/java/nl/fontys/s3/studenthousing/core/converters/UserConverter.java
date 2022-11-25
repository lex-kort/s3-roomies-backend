package nl.fontys.s3.studenthousing.core.converters;

import nl.fontys.s3.studenthousing.controller.dto.UserDTO;
import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.domain.account.Landlord;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.domain.account.User;
import nl.fontys.s3.studenthousing.persistence.entity.account.LandlordEntity;
import nl.fontys.s3.studenthousing.persistence.entity.account.StudentEntity;
import nl.fontys.s3.studenthousing.persistence.entity.account.UserEntity;

public class UserConverter {
    private UserConverter(){}

    public static User convertToDomain(UserDTO user){
        UserRoles role = getRole(user.getUserRole());
//        String role = user.getUserRole();
        switch(role){
            default:
            case STUDENT:
                return Student.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .name(user.getName())
                        .prefix(user.getPrefix())
                        .surname(user.getSurname())
                        .phoneNumber(user.getPhoneNumber())
                        .userRole(role)
                        .studentNumber(user.getStudentNumber())
                        .build();

            case LANDLORD:
                return Landlord.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .name(user.getName())
                        .prefix(user.getPrefix())
                        .surname(user.getSurname())
                        .phoneNumber(user.getPhoneNumber())
                        .userRole(role)
                        .companyName(user.getCompanyName())
                        .cocNumber(user.getCocNumber())
                        .address(user.getAddress())
                        .zipCode(user.getZipCode())
                        .city(user.getCity())
                        .build();
        }
    }

    public static User convertToDomain(UserEntity user){
        UserRoles role = getRole(user.getUserRole());
//        String role = user.getUserRole();
        switch(role){
            default:
            case STUDENT:
                StudentEntity student = (StudentEntity) user;
                return Student.builder()
                        .email(student.getEmail())
                        .password(student.getPassword())
                        .name(student.getName())
                        .prefix(student.getPrefix())
                        .surname(student.getSurname())
                        .phoneNumber(student.getPhoneNumber())
                        .userRole(role)
                        .studentNumber(student.getStudentNumber())
                        .build();

            case LANDLORD:
                LandlordEntity landlord = (LandlordEntity) user;
                return Landlord.builder()
                        .email(landlord.getEmail())
                        .password(landlord.getPassword())
                        .name(landlord.getName())
                        .prefix(landlord.getPrefix())
                        .surname(landlord.getSurname())
                        .phoneNumber(landlord.getPhoneNumber())
                        .userRole(role)
                        .companyName(landlord.getCompanyName())
                        .cocNumber(landlord.getCocNumber())
                        .address(landlord.getAddress())
                        .zipCode(landlord.getZipCode())
                        .city(landlord.getCity())
                        .build();
        }
    }

    public static UserDTO convertToDTO(User user){
        UserRoles role = user.getUserRole();
//        String role = user.getUserRole();
        switch(role){
            default:
            case STUDENT:
                Student student = (Student)user;
                return UserDTO.builder()
                        .id(student.getId())
                        .email(student.getEmail())
                        .password(student.getPassword())
                        .name(student.getName())
                        .prefix(student.getPrefix())
                        .surname(student.getSurname())
                        .phoneNumber(student.getPhoneNumber())
                        .userRole(role.name())
                        .studentNumber(student.getStudentNumber())
                        .build();

            case LANDLORD:
                Landlord landlord = (Landlord)user;
                return UserDTO.builder()
                        .id(landlord.getId())
                        .email(landlord.getEmail())
                        .name(landlord.getName())
                        .prefix(landlord.getPrefix())
                        .surname(landlord.getSurname())
                        .phoneNumber(landlord.getPhoneNumber())
                        .userRole(role.name())
                        .companyName(landlord.getCompanyName())
                        .cocNumber(landlord.getCocNumber())
                        .address(landlord.getAddress())
                        .zipCode(landlord.getZipCode())
                        .city(landlord.getCity())
                        .build();
        }
    }

    public static UserEntity convertToEntity(User user){
        UserRoles role = user.getUserRole();
//        String role = user.getUserRole();
        switch(role){
            default:
            case STUDENT:
                Student student = (Student)user;
                return StudentEntity.builder()
                        .id(student.getId())
                        .email(student.getEmail())
                        .password(student.getPassword())
                        .name(student.getName())
                        .prefix(student.getPrefix())
                        .surname(student.getSurname())
                        .phoneNumber(student.getPhoneNumber())
                        .userRole(role.name())
                        .studentNumber(student.getStudentNumber())
                        .build();

            case LANDLORD:
                Landlord landlord = (Landlord)user;
                return LandlordEntity.builder()
                        .id(landlord.getId())
                        .email(landlord.getEmail())
                        .name(landlord.getName())
                        .prefix(landlord.getPrefix())
                        .surname(landlord.getSurname())
                        .phoneNumber(landlord.getPhoneNumber())
                        .userRole(role.name())
                        .companyName(landlord.getCompanyName())
                        .cocNumber(landlord.getCocNumber())
                        .address(landlord.getAddress())
                        .zipCode(landlord.getZipCode())
                        .city(landlord.getCity())
                        .build();
        }
    }

    private static UserRoles getRole(String input){
        UserRoles role;
        try{
            role = UserRoles.valueOf(input.toUpperCase());
        }
        catch(IllegalArgumentException e){
            role = UserRoles.STUDENT;
        }
        return role;
    }
}
