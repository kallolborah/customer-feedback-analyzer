# The Customer Feedback Analyzer
This is a tool to understand customer feedback from unstructured data. The tool currently analyses sentiment but in the future can enable specifying entities of particular interest (eg, products launched in a market) and generating sentiment analysis for those entities. 

## Application components
1. Posting function - this is a AWS Lambda function that posts collects and posts input data into a AWS S3 bucket. The collection of data could be from a form or as a REST API that uses the Lambda function endpoint. 
2. Sentiment analyzer - this component uses Amazon Comprehend to generate sentiment analysis from data in the input S3 bucket, and posts the generated report in a output S3 bucket.
3. Fetching function - this is a AWS Lambda function that fetches sentiment reports from the output AWS S3 bucket. This function endpoint can be called from the browser or used as a REST API from another application that uses this system. 

## Building it locally
1. [Install JDK 11 or more](https://adoptopenjdk.net/) : this is to be able to compile and build the application locally
2. [Configure AWS Command Line Interface (CLI)](https://docs.aws.amazon.com/comprehend/latest/dg/setup-awscli.html) : this is to set connectivity to the AWS account where the application needs to be deployed. It uses the deployer's AWS IAM credentials.
3. [Install Maven](https://maven.apache.org/install.html) : this is to package the application with dependencies such as the AWS SDK and other AWS components such as S3 and Lambda functions.
4. [Install AWS Java SDK](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-install.html) : this includes all the libraries for AWS Comprehend, S3 and other utilities used by the application.
5. Run 
```
$> git clone https://github.com/kallolborah/customer-feedback-analyzer
$> cd customer-feedback-analyzer
$> mvn package
$> java -cp target\sentiment-analysis-1.0-SNAPSHOT.jar org.product.feedback.App
```
## Deploying the application build



## Running it remotely



## APIs for integrating it with another application
