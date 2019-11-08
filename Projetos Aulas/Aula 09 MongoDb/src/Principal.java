import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.User;

public class Principal {

	public static void main(String[] args) {
		System.out.println("Inicio MongoDb");
		MongoClient mC = new MongoClient("localhost");
		
		exemplo01ListarDatabasesECollectionsDeUmBanco(mC);
		exemplo02Um(mC);
		exemplo03InserirMuitos(mC);
		exemplo04Buscar(mC);
		exemplo05BuscarPorId(mC);

		mC.close();
		System.out.println("Fim MongoDb");
	}
	
	public static void exemplo08PegarOId(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		Document doc = coll.find(Document.parse("{nome: 'Wesley'}")).first();
		
		ObjectId id = doc.getObjectId("_id");
		String idString = id.toString();
		ObjectId novoId = new ObjectId(idString);
		
		System.out.println(idString);
		System.out.println(novoId);
	}
	
	public static void exemplo07Deletar(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		coll.deleteOne(Document.parse("{nome: 'Pedro'}"));
		
	}
	
	public static void exemplo06AtualizarMongo(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		// Buscar usuário por Id
		/*
		Document doc = coll.find(Document.parse("{id: ''}")).first();
		User user = User.fromDocument(doc);
		 */
		
		
		User user = new User("Lima", "lima123@mail.com", 19);
		
		Document update = new Document();
		update.append("$set", user.toDocument());
		
		coll.updateOne(Document.parse("{nome: 'Lima'}"), update);
	}
	
	public static void exemplo05BuscarPorId(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		System.out.println("Número de elementos: " + coll.countDocuments());
		
//		for(Document doc : coll.find(Document.parse("{_id: new ObjectId('')}"))) {
		for(Document doc : coll.find(Document.parse("{nome: 'Wesley'}"))) {
			User user = User.fromDocument(doc);
			System.out.println(user);
		}
	}
	
	public static void exemplo04Buscar(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		for(Document doc : coll.find()) {
			User user = User.fromDocument(doc);
			System.out.println(user);
		}
	}
	
	public static void exemplo03InserirMuitos(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("users");
		
		List<Document> docs = new ArrayList<Document>();
		
		docs.add(new User("Wesley", "wesley@email.com", 20).toDocument());
		docs.add(new User("pedro", "pedro@email.com", 21).toDocument());
		docs.add(new User("lima", "lima@email.com", 22).toDocument());
		
		coll.insertMany(docs);
	}
	
	
	public static void exemplo02Um(MongoClient mC) {
		MongoDatabase db = mC.getDatabase("dsp");
		MongoCollection<Document> coll = db.getCollection("cores");
		System.out.println(coll);
		
//		User user = new User("Wesley", "wp.mail.com", 20);
//		Document doc = Document.parse(user.toJson());
//		coll.insertOne(doc);
		
		coll.insertOne(new User("WP", "wp@w", 20).toDocument());
	}
	
	public static void exemplo01ListarDatabasesECollectionsDeUmBanco(MongoClient mC) {
//		System.out.println(mC);
		
//		MongoDatabase db = mongoClient.getDatabase("dsp");
//		System.out.println(db);
		
		for(String nomeDb : mC.listDatabaseNames()) {
			System.out.println(nomeDb);
			MongoDatabase db = mC.getDatabase(nomeDb);
			for(String s : db.listCollectionNames()) {
				System.out.println(s);
			}
		}
		
	}
}
