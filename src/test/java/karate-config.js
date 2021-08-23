function fn() {
	var env = karate.env; // get the java system property 'karate.env'
	
	if (!env) {
		env = 'dev'; // a custom default
	}

	var config = {
		// all have http://localhost:8080
		loginUrl: 'http://localhost:8080/users'
		// khine url
		// ivan url
		// registerUrl: 'http://localhost:8080/users' // not using because there's input in the url
	}
	
	// don't waste time waiting for a server I didn't start
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 5000);
	return config;
}