/**********************************
 * Copyright (c)  @Da Costa David *
 **********************************/

package CoreUtils;

/**
 * A quadruplet data structure
 *
 * @param FirstValue  the first value of the data structure
 * @param SecondValue the second value of the data structure
 * @param ThirdValue  the third value of the data structure
 * @param FourthValue the fourth value of the data structure
 * @param <T>         the type of the first value
 * @param <E>         the type of the second value
 * @param <S>the      type of the third value
 * @param <V>the      type of the fourth value
 */
public record FQuadruplet<T, E, S, V>(T FirstValue, E SecondValue, S ThirdValue, V FourthValue) {
}