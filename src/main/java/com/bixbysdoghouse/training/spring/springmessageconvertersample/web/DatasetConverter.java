package com.bixbysdoghouse.training.spring.springmessageconvertersample.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DatasetConverter implements HttpMessageConverter<Dataset> {
    private static final Map<MediaType, Lang> SUPPORTED_MEDIA_TYPES = new LinkedHashMap<>();

    static {
        SUPPORTED_MEDIA_TYPES.put(MediaType.valueOf("application/n-quads"), Lang.NQUADS);
        SUPPORTED_MEDIA_TYPES.put(MediaType.valueOf("application/ld+json"), Lang.JSONLD);
        SUPPORTED_MEDIA_TYPES.put(MediaType.valueOf("application/ld+json;charset=UTF-8"), Lang.JSONLD);
        SUPPORTED_MEDIA_TYPES.put(MediaType.valueOf("application/n-quads;charset=UTF-8"), Lang.JSONLD);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        log.info(">>>canRead: clazz={}, mediaType={}", clazz, mediaType);
        boolean retVal = Dataset.class.isAssignableFrom(clazz) &&
                SUPPORTED_MEDIA_TYPES.containsKey(mediaType);
        log.info("<<<canRead: clazz={}, mediaType={} -> {}", clazz, mediaType, retVal);
        return retVal;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        log.info(">>>canWrite: clazz={}, mediaType={}", clazz, mediaType);
        boolean retVal = Dataset.class.isAssignableFrom(clazz) &&
                SUPPORTED_MEDIA_TYPES.containsKey(mediaType);
        log.info("<<<canWrite: clazz={}, mediaType={}", clazz, mediaType);
        return retVal;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        log.info(">>>getSupportedMediaTypes: ");
        List<MediaType> mediaTypes = new ArrayList<>(SUPPORTED_MEDIA_TYPES.keySet());
        log.info("<<<getSupportedMediaTypes: -> {}", mediaTypes);
        return mediaTypes;
    }

    @Override
    public Dataset read(Class<? extends Dataset> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        log.info(">>>read: clazz={}, inputMessage={}", clazz, inputMessage);
        Lang lang = SUPPORTED_MEDIA_TYPES.get(inputMessage.getHeaders().getContentType());
        Dataset newDataset = DatasetFactory.create(ModelFactory.createDefaultModel());
        RDFDataMgr.read(newDataset, inputMessage.getBody(), lang);
        log.info("<<<read: clazz={}, inputMessage={} -> {}", clazz, inputMessage, newDataset);
        return newDataset;
    }

    @Override
    public void write(Dataset dataset, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        log.info(">>>write: clazz={}, contentType={}, outputMessage={}", dataset, contentType, outputMessage);
        Lang lang = SUPPORTED_MEDIA_TYPES.get(contentType);
        RDFDataMgr.write(outputMessage.getBody(), dataset, lang);
        //outputMessage.getHeaders().setAccept(contentType);
        log.info("<<<write: clazz={}, contentType={}, outputMessage={}", dataset, contentType, outputMessage);
    }
}
