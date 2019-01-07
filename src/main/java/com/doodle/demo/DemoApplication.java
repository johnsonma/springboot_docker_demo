package com.doodle.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	MongoTemplate mongoTemplate;

	@RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
	public List<PollRecord> list(@PathVariable("name") String name,
			@RequestParam(value = "start", required = false) Long dateStart,
			@RequestParam(value = "end", required = false) Long dateEnd,
			@RequestParam(value = "text", required = false) String text) {
		Query query = new Query();

		Criteria criteria = Criteria.where("initiator.name").is(name);
		List<Criteria> criteriaList = new ArrayList<>();
		if (dateStart != null) {
			criteriaList.add(Criteria.where("initiated").gte(dateStart));
		}
		if (dateEnd != null) {
			criteriaList.add(Criteria.where("initiated").lte(dateEnd));
		}

		if (StringUtils.isNotEmpty(text)) {
			Criteria regCriteria = new Criteria();
			Pattern pattern = Pattern.compile("^.*" + text + ".*$",
					Pattern.CASE_INSENSITIVE);
			regCriteria.orOperator(Criteria.where("title").regex(pattern),
					Criteria.where("description").regex(pattern), Criteria
							.where("options.text").regex(pattern));
			criteriaList.add(regCriteria);
		}

		Criteria[] criteriaArray = criteriaList.toArray(new Criteria[0]);
		if (criteriaArray.length > 0) {
			criteria.andOperator(criteriaArray);
		}
		
		query.addCriteria(criteria);
		List<PollRecord> list = mongoTemplate.find(query, PollRecord.class);
		return list;
	}

	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	public List<PollRecord> get(@PathVariable("name") String name,
			@RequestParam(value = "text", required = false) String text) {
		// Query query = new Query();
		Criteria criteria = Criteria.where("initiator.name").is(name);
		TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
				.onField("title").onField("description").build();

		mongoTemplate.indexOps(PollRecord.class).ensureIndex(textIndex);

		TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(
				text);

		Query query = TextQuery.queryText(textCriteria);
		query.addCriteria(criteria);

		List<PollRecord> list = mongoTemplate.find(query, PollRecord.class);
		return list;
	}

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
