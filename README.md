# Automated Mapping Study

This library is built as a standalone Java library for conducting automated mapping studies.

## Getting Started

These instructions will get you a copy of the project up and running.

### Prerequisites

There are a couple prerequisites to cover before running.

#### Installing ChromeDriver

1. [Download the Driver](https://chromedriver.chromium.org/downloads)
2. Edit the driver location in the Runner.java

#### Installing NLP Binaries

Download the `opennlp` trained models for sentence detection and part-of-speech tagging. You can find these two models (trained on various languages) on [opennlp's model page](http://opennlp.sourceforge.net/models-1.5/). For example, you could use the English versions of the [sentence detection](http://opennlp.sourceforge.net/models-1.5/en-sent.bin) and [POS-tagger](http://opennlp.sourceforge.net/models-1.5/en-pos-maxent.bin) models. You'll specify the file paths to these models in the Runner.java.

#### API Keys

Add following API keys as environment variables:

```
export IEEE_API_KEY= ...
export SD_API_KEY= ...
```

If API keys are not defined, then Selenium will be used to collect papers.

## Run database using Docker

```
$ docker run -p 5432:5432 -e POSTGRES_USER=amsdev -e POSTGRES_PASSWORD=amsdev -e POSTGRES_DB=ams postgres
```

## Run project

```
$ mvn spring-boot:run
```

## Query export endpoint

`POST` to `localhost:8080/queryexport` with the JSON payload:

```
{
    "query" : "abstract: ('mapping study' OR 'literature review') AND title: tool",
    "downloadPapers" : false
}
```

- Available scope specifiers: `title`, `abstract`, `keywords`, `fulltext`, and `all`
- Multiple words must be quoted e.g. `'mapping study'`

## Authors

* [**Andrew Walker**](https://github.com/walker76)
* [**Dipta Das**](https://github.com/diptadas)
* [**Vincent Bushong**](https://github.com/vinbush)

## Acknowledgments

This material is based upon work supported by the National Science Foundation under Grant No. 1854049 and a grant from [Red Hat Research](https://research.redhat.com).
