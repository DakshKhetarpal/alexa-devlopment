/* eslint-disable  func-names */
/* eslint-disable  no-console */

const Alexa = require('ask-sdk');

const APP_ID = 'amzn1.ask.skill.69f3234c-dd9a-4d38-a061-34fc4ec054ca';

const SKILL_NAME = 'Airtel Payment Bank';

const HELP_MESSAGE = 'You can ask me anything to do in your Airtel Payments Bank Account, or, you can say exit... What can I help you with?';
const HELP_REPROMPT = 'What can I help you with?';
const STOP_MESSAGE = 'Goodbye!';
const TRANSACTION_BEGIN_MESSAGE = 'These are your last five transactions';
CHANGE_MPIN_MESSAGE = 'This is to change your MPIN, Do you wish to continue';
// Hard Coaded Data for Testing

var data = {
  Name: 'John Doe',
  Number: '12345678',
  Balance: 100
};

const transactions = [
  'Paid 500 rupees to Ellen',
  'Received 1000 rupees from Conan',
  'Paid 100 rupees to Stephen',
  'Paid 200 rupees to Jimmy',
  'Received 50 rupees from Craig'
  
  
  
];

var merchants = {
      'water board' : {
          'balance' : 1000
      },
      'electricity board' : {
          'balance' : 1000
      }
}

// Intent Handlers After This

const launchAndHelpHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return request.type === 'LaunchRequest';
  },
  handle(handlerInput) {
    
    const speechOutput = HELP_MESSAGE;
    return handlerInput.responseBuilder
      .speak(speechOutput)
      .withSimpleCard(SKILL_NAME)
      .withShouldEndSession(false)
      .getResponse();
  },
};
const BalanceHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return (request.type === 'IntentRequest'
      && request.intent.name === 'Balance');
  },
  handle(handlerInput) {
    
    const speechOutput = 'Your Balance is,'+data.Balance+' rupees';

    return handlerInput.responseBuilder
      .speak(speechOutput)
      .withSimpleCard(SKILL_NAME)
         .withShouldEndSession(false)
      .getResponse();
  },
};

const LastTransactionsHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return (request.type === 'IntentRequest'
      && request.intent.name === 'lastFiveTransactions');
  },
  handle(handlerInput) {
    
    const speechOutput = TRANSACTION_BEGIN_MESSAGE + '. ' + transactions[0] + '. ' + transactions[1] + '. ' + transactions[2] + '. ' + transactions[3] + '. ' + transactions[4];

    return handlerInput.responseBuilder
      .speak(speechOutput)
      .withSimpleCard(SKILL_NAME)
         .withShouldEndSession(false)
      .getResponse();
  },
};

const ChangeMpinHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return (request.type === 'IntentRequest'
      && request.intent.name === 'ChangeMpin');
  },
  handle(handlerInput) {
    
    const speechOutput = CHANGE_MPIN_MESSAGE;

    return handlerInput.responseBuilder
      .speak(speechOutput)
      .withSimpleCard(SKILL_NAME)
     // .reprompt()
         .withShouldEndSession(false)
      .getResponse();
  },
};

const HelpIntentHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return request.type === 'IntentRequest'
      && request.intent.name === 'AMAZON.HelpIntent';
  },
  handle(handlerInput) {
    return handlerInput.responseBuilder
      .speak(HELP_MESSAGE)
      .reprompt(HELP_REPROMPT)
      .getResponse();
  },
};

const ExitHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return request.type === 'IntentRequest'
      && (request.intent.name === 'AMAZON.CancelIntent'
        || request.intent.name === 'AMAZON.StopIntent');
  },
  handle(handlerInput) {
    return handlerInput.responseBuilder
      .speak(STOP_MESSAGE)
      .getResponse();
  },
};

const SessionEndedRequestHandler = {
  canHandle(handlerInput) {
    const request = handlerInput.requestEnvelope.request;
    return request.type === 'SessionEndedRequest';
  },
  handle(handlerInput) {
    console.log(`Session ended with reason: ${handlerInput.requestEnvelope.request.reason}`);

    return handlerInput.responseBuilder.getResponse();
  },
};

const ErrorHandler = {
  canHandle() {
    return true;
  },
  handle(handlerInput, error) {
    console.log(`Error handled: ${error.message}`);

    return handlerInput.responseBuilder
      .speak('Sorry, an error occurred.')
      .reprompt('Sorry, an error occurred.')
      .getResponse();
  },
};

const skillBuilder = Alexa.SkillBuilders.standard();

exports.handler = skillBuilder
  .addRequestHandlers(
    launchAndHelpHandler,
    LastTransactionsHandler,
    ChangeMpinHandler,
    BalanceHandler,
    //billPayment,
    HelpIntentHandler,
    ErrorHandler,
    SessionEndedRequestHandler,
    ExitHandler
  )
  .addErrorHandlers(ErrorHandler)
  .lambda();
