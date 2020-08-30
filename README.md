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

## Authors

* [**Andrew Walker**](https://github.com/walker76)

## Acknowledgments

This material is based upon work supported by the National Science Foundation under Grant No. 1854049 and a grant from [Red Hat Research](https://research.redhat.com).