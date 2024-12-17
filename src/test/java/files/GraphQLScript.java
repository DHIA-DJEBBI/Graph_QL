package files;


import  static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// Query 
		
		String response = given().log().all().header("content-type" , "application/json").body("{\r\n"
				+ "  \"query\": \"query($characterId : Int!,$episodeId : Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 7) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId){\\n    id\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name:\\\"rahul\\\"}){\\n    info{count}\\n    result{name , type}\\n  }\\n  \\n  episodes(filters:{episode: \\\"hulu\\\"}){\\n    result{\\n      id \\n      name \\n      air_date\\n      episode\\n      \\n    }\\n    \\n  }\\n\\n  \\n}\\n\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"characterId\": 8,\r\n"
				+ "    \"episodeId\": 8\r\n"
				+ "  }\r\n"
				+ "}").when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response); 
		System.out.println("the name extracted is  "+js.getString("data.episode.name")); 
		
		
	//	Mutation Response 
		

		// String caracterName = "Djebbi Dhia";
		
		String MutationResponse = given().log().all().header("content-type" , "application/json").body("{\r\n"
				+ "  \"query\": \"mutation($locationName:  String! ,$characterName : String! , $episodeName : String!){\\n  \\n  createLocation(location: {name:$locationName,type:\\\"Southzone\\\",dimension:\\\"234\\\"}){\\n    id\\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"dead\\\",species: \\\"DJebbi\\\", gender:\\\"male\\\",image:\\\"png\\\",locationId:1602,originId:1602}){\\n    \\n    id\\n    \\n  }\\n  createEpisode(episode: {name:$episodeName,air_date:\\\"1050 June\\\",episode: \\\"Netflix\\\"}){\\n    \\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[1,3,14,5]){\\n    \\n    locationsDeleted\\n    \\n  }\\n  \\n  \\n}\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"locationName\": \"Tunisia\",\r\n"
				+ "    \"characterName\": \"Beja\",\r\n"
				+ "    \"episodeName\": \"Fa9rr\"\r\n"
				+ "  }\r\n"
				+ "}").when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(MutationResponse);
		JsonPath jm = new JsonPath(MutationResponse); 
		
		System.out.println("the id  extracted from immutation  is  "+jm.getString("data.createLocation.id")); 
		
		
		
		
		
	}

}
