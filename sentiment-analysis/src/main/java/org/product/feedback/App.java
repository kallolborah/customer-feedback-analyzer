package org.product.feedback;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

/**
 * Sentiment analysis for content pushed to S3
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String text = "I've always had a need for a good mouse. Classic mouse have given me wrist pain after especially long sessions. First and foremost, it looks and feels fantastic in my hand. It didn't take me long to get used to the new position. It felt completely normal after just a few minutes, and it is very light. The mouse's overall body is made of a plastic polymer, making it both durable and light. The textured surface strengthens grip and makes the overall user experience more pleasant. The left and right buttons are around the right side, with the scroll wheel in the center. The scroll wheel is covered with the same rubber as the rest of the mouse. It's silky smooth, with a subtle click. Two more adjustable buttons can be found around the left side. These are located just above your thumb and are easy to hit, even if your hands are small. To save battery life, it has a simple on/off button on the bottom. This mouse is fantastic in general. I've never used a mouse as comfortable as this one. Definitely recommend this mouse if you want a comfortable and easy-to-use product!";

        // Create credentials using a provider chain. For more information, see
        // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
 
        AmazonComprehend comprehendClient =
            AmazonComprehendClientBuilder.standard()
                                         .withCredentials(awsCreds)
                                         .withRegion("us-east-1")
                                         .build();
                                         
        // Call detectSentiment API
        System.out.println("Calling DetectSentiment");
        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
                                                                                    .withLanguageCode("en");
        DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
        System.out.println(detectSentimentResult);
        System.out.println("End of DetectSentiment\n");
        System.out.println( "Done" );
    }
}
