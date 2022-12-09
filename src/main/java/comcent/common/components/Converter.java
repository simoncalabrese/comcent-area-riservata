package comcent.common.components;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Converter<A, B> extends Function<A,B>{
    default Collection<B> applyToList(Collection<A> collaA) {
        return collaA.stream().map(this::apply).collect(Collectors.toList());
    }

}
