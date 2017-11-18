# ReadTextMessages

This small app includes Permission Activity that dynamically asks user to put permissions for receiving SMS and reading SMS.
Once you permits SMS permissions, you are redirected into MainActivity that shows all of your text messages. 
When receiving texts, BroadCastReceiver would take user back into MainActivity where you log the text message body.
