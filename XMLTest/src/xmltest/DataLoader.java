package xmltest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader {
	
	private ArrayList<Player> playerlist;

	public void load() throws Exception {
		
		playerlist = new ArrayList<>();
		
		File xmlfile = new File("premierLeaguePlayerNames.xml");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(xmlfile);
		NodeList mylist = doc.getElementsByTagName("row");
		
		for(int i=0; i<mylist.getLength(); i++) {
			Node n = mylist.item(i);
			Element e = (Element)n;

			String name = e.getElementsByTagName("name").item(0).getTextContent();
			String club = e.getElementsByTagName("club").item(0).getTextContent();
			double value =  Double.parseDouble(e.getElementsByTagName("market_value").item(0).getTextContent());
			String pos = e.getElementsByTagName("position").item(0).getTextContent();
			int age = Integer.parseInt(e.getElementsByTagName("age").item(0).getTextContent());
			String nation = e.getElementsByTagName("nationality").item(0).getTextContent();

			Player newplayer = new Player(name, nation, club, age, pos, value);
			playerlist.add(newplayer);
		}
		
	}
	
	public void updateXML() {
		
	}
	
	public void saveNewXML() throws Exception{
		
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();

        Document doc = db.newDocument();

        Element root = doc.createElement("root");
        doc.appendChild(root);

        Element row = doc.createElement("row");
        root.appendChild(row);

        for(Player p : playerlist) {
        	
        	Element playerName = doc.createElement("name");
            playerName.appendChild(doc.createTextNode( p.getName()) );
            root.appendChild(playerName);
            
            
        	Element playerClub = doc.createElement("club");
        	playerClub.appendChild(doc.createTextNode( p.getClub()) );
            root.appendChild(playerClub);
            
        	Element playerAge = doc.createElement("age");
        	playerAge.appendChild(doc.createTextNode( Integer.toString(p.getAge())) );
            root.appendChild(playerAge);
            
        	Element playerPos = doc.createElement("position");
        	playerPos.appendChild(doc.createTextNode( p.getPosition()) );
            root.appendChild(playerAge);
            
        	Element playerMarketVal = doc.createElement("market_value");
        	playerMarketVal.appendChild(doc.createTextNode( Double.toString(p.getMarketValue())) );
            root.appendChild(playerMarketVal);
            
        	Element playerNation = doc.createElement("nationality");
        	playerNation.appendChild(doc.createTextNode( p.getNation()) );
            root.appendChild(playerNation);
            
            
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File("myPlayerList.xml"));

        transformer.transform(domSource, streamResult);
   
	}
	
	public void printAllPlayers() {
		for(Player p : playerlist) {
			System.out.println(p.toString());
		}
	}
	
	public void addPlayer() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		while(true) {
			
			System.out.println("Add new player? (Y/N)");
			input = scan.nextLine();
			
			if(input.equalsIgnoreCase("y")) {
				
				System.out.println("Name?");
				String name_str = scan.nextLine();
				
				System.out.println("Age?");
		        String age_str = scan.nextLine();
		        
				System.out.println("Club?");
		        String club_str = scan.nextLine();
		        
				System.out.println("Nation?");
		        String nation_str = scan.nextLine();
		        
				System.out.println("Position?");
		        String pos_str = scan.nextLine();
		        
				System.out.println("Market value?");
		        String value_str = scan.nextLine();
		        
		        Player newPlayer = new Player(name_str, nation_str, club_str, Integer.parseInt(age_str), pos_str.toUpperCase(), Double.parseDouble(value_str)); 
				playerlist.add(newPlayer);				
		        
			}
			
			else if(input.equalsIgnoreCase("n")) {
				break;
			}
		}
		
	}
	
	public DataLoader() throws Exception {
		load();
		addPlayer();
		printAllPlayers();
		saveNewXML();
	}

	public static void main(String[] args) throws Exception {
		new DataLoader();
	}

}
