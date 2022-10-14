package nl.fontys.s3.studenthousing.common.interfaces;

public interface ObjectConverter<TInput, TOutput> {
    TOutput convert(TInput object);
}
