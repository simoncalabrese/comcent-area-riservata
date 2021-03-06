package comcent.service.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import comcent.common.builders.QueryParamsBuilder;
import comcent.common.components.AbstractPropertiesConfig;
import comcent.common.components.Converter;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class AbstractService {

    @Autowired
    private AbstractPropertiesConfig abstractPropertiesConfig;

    @Autowired
    private ObjectMapper mapper;

    private HttpURLConnection buildConnection(final QueryParamsBuilder builder, final ApiEnum apiEnum) throws IOException {
        final String urlAsString = Stream.of(
                abstractPropertiesConfig.getBasePath(),
                abstractPropertiesConfig.getApi().get(apiEnum.getApiMapKey()))
                .collect(Collectors.joining());
        final String queryParams = Optional.ofNullable(builder).map(QueryParamsBuilder::build).orElse("");
        final URL url = new URL(urlAsString + queryParams);
        return (HttpURLConnection) url.openConnection();
    }

    public <RETURN_CLASS, JSON_CLASS> RETURN_CLASS doGetCall(final Class<JSON_CLASS> clazz,
                                                             final ApiEnum apiEnum,
                                                             final QueryParamsBuilder builder,
                                                             final Converter<JSON_CLASS, RETURN_CLASS> func) throws BaseException {
        try {
            final HttpURLConnection connection = buildConnection(builder, apiEnum);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() == 200) {
                return convertObject(mapper.readValue(copyInputStream(connection.getInputStream()), clazz), func);
            } else {
                throw new BaseException(Suppliers.CONNECTION_RESULT_ERROR);

            }
        } catch (IOException e) {
            throw new BaseException(Suppliers.CONNECTION_ERROR);
        }
    }

    public <RETURN_CLASS> List<RETURN_CLASS> doGetCallList(final Class<RETURN_CLASS> clazz,
                                                           final ApiEnum apiEnum,
                                                           final QueryParamsBuilder builder) throws BaseException {

        try {
            final HttpURLConnection connection = buildConnection(builder, apiEnum);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() == 200) {
                return mapper.readValue(copyInputStream(connection.getInputStream()), mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
            } else {
                throw new BaseException(Suppliers.CONNECTION_RESULT_ERROR);

            }
        } catch (IOException e) {
            throw new BaseException(Suppliers.CONNECTION_ERROR);
        }
    }

    public <RETURN_CLASS, JSON_CLASS, INPUT_CLASS> RETURN_CLASS doPostCall(final Class<JSON_CLASS> clazz,
                                                                           final ApiEnum apiEnum,
                                                                           final INPUT_CLASS inputObject,
                                                                           final Converter<JSON_CLASS, RETURN_CLASS> func)
            throws BaseException {
        try {
            final String json = mapper.writeValueAsString(inputObject);
            final HttpURLConnection connection = buildConnection(null, apiEnum);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            final DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(json.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
            if (connection.getResponseCode() == 200) {
                final InputStream inputStream = copyInputStream(connection.getInputStream());
                return convertObject(mapper.readValue(inputStream, clazz), func);
            } else {
                throw new BaseException(Suppliers.CONNECTION_RESULT_ERROR);
            }
        } catch (IOException e) {
            throw new BaseException(Suppliers.CONNECTION_ERROR);
        }
    }

    public <RETURN_CLASS, INPUT_CLASS> List<RETURN_CLASS> doPostCallList(final Class<RETURN_CLASS> clazz,
                                                                         final ApiEnum apiEnum,
                                                                         final INPUT_CLASS inputObject) throws BaseException {
        try {
            final String json = mapper.writeValueAsString(inputObject);
            final HttpURLConnection connection = buildConnection(null, apiEnum);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            final DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(json.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
            if (connection.getResponseCode() == 200) {
                final InputStream inputStream = copyInputStream(connection.getInputStream());
                return mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
            } else {
                throw new BaseException(Suppliers.CONNECTION_RESULT_ERROR);
            }
        } catch (IOException e) {
            throw new BaseException(Suppliers.CONNECTION_ERROR);
        }
    }


    private InputStream copyInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] barr = new byte[256];
        int lenn;
        while ((lenn = inputStream.read(barr)) > -1) {
            baos.write(barr, 0, lenn);
        }
        baos.flush();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    protected <SRC, DST> DST convertObject(final SRC src, final Converter<SRC, DST> func) {
        return func.apply(src);
    }

    protected <SRC> SRC cloneObject(final SRC src) {
        try {
            final SRC clone = (SRC) src.getClass().newInstance();
            BeanUtils.copyProperties(src, clone);
            return clone;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    protected <SRC, DST> DST convertObjectCloned(final SRC src, final Converter<SRC, DST> func) {
        return convertObject(cloneObject(src), func);
    }
}
