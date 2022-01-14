# The Customer Feedback Analyzer
This is a tool to understand customer feedback from unstructured data. The tool currently analyses sentiment but in the future can enable specifying entities of particular interest (eg, products launched in a market) and generating sentiment analysis for those entities. 

## Application components
1. Posting function - this is a AWS Lambda function that posts collects and posts input data into a AWS S3 bucket. The collection of data could be from a form or as a REST API that uses the Lambda function endpoint. 
2. Sentiment analyzer - this component uses Amazon Comprehend to generate sentiment analysis from data in the input S3 bucket, and posts the generated report in a output S3 bucket.
3. Fetching function - this is a AWS Lambda function that fetches sentiment reports from the output AWS S3 bucket. This function endpoint can be called from the browser or used as a REST API from another application that uses this system. 

## Checking sentiment analysis locally on static text
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

## Checking sentiment analysis on dynamic feedback
1. [Install AWS SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html) : this is required to deploy AWS Lambda functions.
2. [Install NodeJS 12.x](https://nodejs.org/en/download/) : this is used to run Lambda functions on nodejs
3. Run
```
$> git clone https://github.com/kallolborah/customer-feedback-analyzer
$> cd customer-feedback-analyzer
$> cd customer-feedback
$> sam deploy --guided
```

When prompted for parameters, enter:
- Stack Name: s3Uploader
- AWS Region: your preferred AWS Region (e.g. us-east-1)
- Accept all other defaults.

This takes several minutes to deploy. At the end of the deployment, note the output values, as you need these later. The APIendpoint value is important - it looks like https://ab123345677.execute-api.us-west-2.amazonaws.com. Before running, you need to set the API Gateway endpoint from the backend deployment on line 29 in the `index.html` file.


## Deploying the build and running the application remotely
1. [Copy the index.html file in to an S3 bucket](https://docs.aws.amazon.com/AmazonS3/latest/user-guide/upload-objects.html).
2. Select the index.html and make it accessible as a public static URL in 'actions' in S3. Then, go to properties tab and copy the static URL that is displayed towards the bottom of the page.
3. Upload file content on the form displayed when you visit the URL in the browser. It should display the sentiment analysed in the browser and post the sentiment report to S3. You will find a .txt object with the sentiment report in the S3 bucket.


## APIs for integrating it with another application
TODO : API to the Lambda function that authenticates requests need to be done.