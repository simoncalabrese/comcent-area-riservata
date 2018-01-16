package comcent.common.builders;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class QueryParamsBuilder {
    private List<Pair<String, String>> params;

    private QueryParamsBuilder() {
        params = new ArrayList<>();
    }

    public static QueryParamsBuilder getBuilder() {
        return new QueryParamsBuilder();
    }

    public <T> QueryParamsBuilder appendParams(final String key, final T value) {
        final Function<T, String> toString = t -> t.toString().replace(" ","%20");
        params.add(Pair.of(key, toString.apply(value)));
        return this;
    }

    public String build() {
        return params
                .stream()
                .reduce("", (acc, comb) -> {
                    if (StringUtils.isEmpty(acc)) {
                        return acc.concat("?").concat(comb.getKey()).concat("=").concat(comb.getValue());
                    } else {
                        return "&".concat(comb.getKey()).concat("=").concat(comb.getValue());
                    }
                }, (a, b) -> a);
    }
}
