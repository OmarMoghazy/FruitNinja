package command;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import controller.GameActions;
import misc.Difficulty;
import misc.SaveMemento;

public class SaveLoad {
	SaveMemento saveMemento;
	
	public void setMemento(SaveMemento saveMemento) {
		this.saveMemento = saveMemento;
	}
	
	public void save() {
		 try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.newDocument();
	            StringBuilder xmlStringBuilder = new StringBuilder();
	            xmlStringBuilder.append("<?xml version=?> <class> </class>");
	            new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
	            Element Diff = document.createElement("Difficulty");
	            Element Score = document.createElement("Score");
	            Element Lives = document.createElement("Lives");
	            Element Time = document.createElement("Time");
	            document.appendChild(Diff);
	 
	            //Diff.appendChild();
	 
	            Diff.appendChild(document.createTextNode(saveMemento.getDifficulty() +" "));
	            Diff.appendChild(Score);
	            Score.appendChild(document.createTextNode(String.valueOf(saveMemento.getScore()) + " "));
	            Diff.appendChild(Lives);
	            Lives.appendChild(document.createTextNode(String.valueOf(saveMemento.getLives()) + " "));
	            Diff.appendChild(Time);
	            Time.appendChild(document.createTextNode(String.valueOf(saveMemento.getTime()) + " "));
	 
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource domSource = new DOMSource(document);
	            StreamResult streamResult = new StreamResult(new File("Resources/SavedGame.xml"));
	            transformer.transform(domSource, streamResult);
	            System.out.println("Done creating XML File");
	 
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void load() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document = builder.parse("Resources/SavedGame.xml");
            String Data = document.getElementsByTagName("Difficulty").item(0).getTextContent();
            System.out.println(Data);
            ArrayList<String> DataList = new ArrayList<String>();
            for (String val : Data.split(" ")) {
                System.out.println(val);
                DataList.add(val);
            }
 
            String ldiff = DataList.get(0);
            
            if(ldiff.equals("EASY")) GameActions.setDifficulty(Difficulty.EASY);
            else if(ldiff.equals("MEDIUM")) GameActions.setDifficulty(Difficulty.MEDIUM);
            else GameActions.setDifficulty(Difficulty.HARD);
            
            GameActions.setScore(Integer.parseInt(DataList.get(1)));
            
            GameActions.setLives(Integer.parseInt(DataList.get(2)));
            
 
            GameActions.setTime(Integer.parseInt(DataList.get(3)));
            
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
	}
}
