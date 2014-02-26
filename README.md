AsyncFBClient
=============
An Asynchronous light wrapper around uniRest for accessing facebook graph API.
Currently provides only access to user details.

Supply access token in \properties\app.properties and run the main method.

Usage

		Properties props = new Properties();
		props.load(new FileInputStream("properties/app.properties"));
		String ACCESS_TOKEN = props.getProperty("ACCESS_TOKEN");
	
		AsyncFBClient fbC = new OAuth2AsyncFBClient(ACCESS_TOKEN,new UniRestWrapper());
		CompletionNotifier userCompletionNotifier = new CompletionNotifier();
		fbC.getUserDetails(userCompletionNotifier);
		while(!userCompletionNotifier.isDone()){
			Thread.sleep(1000);
		}
		
		if(userCompletionNotifier.status() == Status.Completed){
			User user = userCompletionNotifier.deserialize(User.class);
			System.out.println(user.getFirst_name());
			System.out.println(user.getLast_name());
		}
	
