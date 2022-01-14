'use strict'

const AWS = require('aws-sdk')
AWS.config.update({ region: process.env.AWS_REGION })
const s3 = new AWS.S3()
const Comprehend = new AWS.Comprehend();

// Change this value to adjust the signed URL's expiration
const URL_EXPIRATION_SECONDS = 300

// Main Lambda entry point
exports.handler = async (event) => {
  return await getUploadURL(event)
}

const getUploadURL = async function(event) {
  const randomID = parseInt(Math.random() * 10000000)
  const Key = `data/${randomID}.txt`

  // Get signed URL from S3
  const s3Params = {
    Bucket: process.env.UploadBucket,
    Key,
    Expires: URL_EXPIRATION_SECONDS,
    ContentType: 'text/plain',

    // This ACL makes the uploaded object publicly readable. You must also uncomment
    // the extra permission for the Lambda function in the SAM template.

    // ACL: 'public-read'
  }

  console.log('Params: ', s3Params)
  const uploadURL = await s3.getSignedUrlPromise('putObject', s3Params)

  await getObj()
  .then(async(parsedData)=>{
    await analyse(parsedData)
    .then(async()=>{
      return JSON.stringify({
        uploadURL: uploadURL,
        Key
      })
    })
  })

  
}

function getObj(params) {
  return S3.getObject(params).promise()
  .then(data => {
      let parsedData = JSON.parse(data.Body.toString());
      return parsedData;
  })
  .catch(err => {
      return err;
  });
}

function analyse(text) {
  let res = [];
  return Comprehend.detectSentiment({

  }).promise()
  .then(async data => {
      for(let i = 0; i < data.KeyPhrases.length; i++) {
            let phrase = await data.KeyPhrases[i].Text;
            res.push(phrase);
      }
      return res;
  })
  .catch(err => {
      return err;
  });
}