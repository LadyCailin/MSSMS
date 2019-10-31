
Dead simple SMS integration! Simply set up your profile, then call `send_sms()`.

## Twilio

Currently, Twilio is the only supported SMS gateway, though the system is set up so that more can be added in the future, if demand is there. First, sign up for a Twilio account [here](https://www.twilio.com/), and obtain your SID and Auth token.

Create a profile in profiles.xml, in your MethodScript directory:


    <profile id="twilioProfile">
        <type>twilio</type>		 
        <sid>AC********************************</sid>		 
        <auth>********************************</auth>
    </profile>

Then, in your code, you can send an sms simply:

    send_sms('twilioProfile', 
        '+11234561212', // from
        '+13214563232', // to
        'Message body');

That's it! You must use the Twilio hosted "active number" as the from number, and in trial mode, you can only send to a validated phone number. With a full account, you can send to any number.

See Twilio's website for more usage details, along with pricing, etc.