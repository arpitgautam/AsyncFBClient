# AsyncFBClient [![Build Status](https://api.travis-ci.org/arpitgautam/AsyncFBClient.png)](https://travis-ci.org/arpitgautam/AsyncFBClient)

=============
An Asynchronous light wrapper around uniRest for accessing facebook graph API.
Currently provides only access to user details.

Supply access token in \properties\app.properties and run the main method.

Usage

		String ACCESS_TOKEN = "";//Supply access token here

		AsyncFBClient fbClient = new OAuth2AsyncFBClient(ACCESS_TOKEN,
				new UniRestWrapper());
		CompletionNotifier notifier = new CompletionNotifier();
		fbClient.getFriendList(notifier);
		while (true) {
			if (!notifier.isDone()) {
				Thread.sleep(100);
				// or do processing here in the meanwhile
			} else {
				Friends friends = notifier.deserialize(Friends.class);
				if (fbClient.hasNext()) {
					fbClient.getNext(notifier);

				} else {
					break;
				}
				//process result from last req here
				for (Datum data : friends.getData()) {
					System.out.println(data.getName());
				}
			}
		}
	}

