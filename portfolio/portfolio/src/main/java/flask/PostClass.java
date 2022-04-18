package flask;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.portfolio.management.portfolio.model.ChartData;
import com.portfolio.management.portfolio.model.Portfolio_Performance;
import com.portfolio.management.portfolio.model.RecommendedAssetWeights;
import com.portfolio.management.portfolio.model.RecommendedPortPerformance;
import com.portfolio.management.portfolio.repository.RecommendedAssetWeightsRepo;

import helper.ChartDataJson;

public class PostClass {
//	public static void main(String[] args) {
//		new PostClass().getPerformanceMeasure();
	


	public static String addr = "http://127.0.0.1:5000/";

//	}
	public Portfolio_Performance getPerformanceMeasure(ArrayList<String> k, ArrayList<Integer> v) {
		Portfolio_Performance p = null;

		ArrayList<Double> value = new ArrayList<Double>();
		for (int i = 0; i < v.size(); i++) {
			double a = (double) v.get(i);
			value.add(a / 100);
		}

		System.out.println("------------------------------------------");
		System.err.println(value);
		System.out.println("------------------------------------------");

		System.out.println("Hi");
		HttpURLConnection conn = null;
		DataOutputStream os = null;
		try {
			URL url = new URL(addr + "/PerformanceMeasure/"); // important to add the trailing slash after add

			MappKeyValue gg = new MappKeyValue(k, value);

			System.out.println("gg - - - - " + gg);

			String[] inputData = { "{" + gg.toString().substring(0, gg.toString().length() - 2) + "}" };

			System.out.println("input data - " + inputData.toString());
			System.out.println("---------------------------------------------");
			for (String input : inputData) {
				System.out.println("input - " + input);

				byte[] postData = input.getBytes(StandardCharsets.UTF_8);
				System.out.println("postData - " + postData);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
				os = new DataOutputStream(conn.getOutputStream());
				os.write(postData);
				os.flush();

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				StringBuilder sb = new StringBuilder();

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					sb.append(output + "\n");
				}
				System.out.println("\n sb to string - \n" + sb.toString());
				ObjectMapper om = new ObjectMapper();
				p = om.readValue(sb.toString().substring(15, sb.toString().length() - 1), Portfolio_Performance.class);

				conn.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		System.out.println(p);
		return p;

	}

	public ArrayList<ChartData> getOptimizePortfolio(ArrayList<String> k, ArrayList<Double> v) {
		ArrayList<ChartData> p = new ArrayList();
		ArrayList<Double> value = new ArrayList<Double>();
		for (int i = 0; i < v.size(); i++) {
			double a = (double) v.get(i);
			value.add(a / 100);
		}

		System.out.println("------------------------------------------");
//		System.err.println(value);
		System.out.println("------------------------------------------");

		System.out.println("Hi");
		HttpURLConnection conn = null;
		DataOutputStream os = null;
		try {
			URL url = new URL(addr + "/OptimizerPortfolio/"); // important to add the trailing slash after add

			MappKeyValue gg = new MappKeyValue(k, value);

			System.out.println("gg - - - - " + gg);

			String[] inputData = { "{" + gg.toString().substring(0, gg.toString().length() - 2) + "}" };

//			System.out.println("input data - " + inputData.toString());
//			System.out.println("---------------------------------------------");
			for (String input : inputData) {
				System.out.println("input - " + input);

				byte[] postData = input.getBytes(StandardCharsets.UTF_8);
				System.out.println("postData - " + postData);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
				os = new DataOutputStream(conn.getOutputStream());
				os.write(postData);
				os.flush();

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println("Output from Server .... \n");

					System.out.println(output);
					sb.append(output + "\n");

				}
				try {
					org.json.JSONObject j = new org.json.JSONObject(sb.toString());
					org.json.JSONArray jArr = j.getJSONArray("result");
//				System.out.println(jArr.get(2).toString());
					System.out.println("json array - " + jArr);

					for (int i = 0; i < jArr.length(); i++) {
						ChartData prtp = null;
						ObjectMapper om = new ObjectMapper();
						prtp = om.readValue((String) jArr.get(i).toString(), ChartData.class);
//						System.out.println("this is prtp - " + prtp);
						p.add(prtp);
					}

					System.out.println("this is p - " + p);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conn.disconnect();


			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		return p;


	}

	public ArrayList<Portfolio_Performance> getPredictionPortfolio(ArrayList<String> k, ArrayList<Double> v) {
		ArrayList<Portfolio_Performance> p = new ArrayList();
		
		ArrayList<Double> value = new ArrayList<Double>();
		for (int i = 0; i < v.size(); i++) {
			double a = (double) v.get(i);
			if(i==0)
			value.add(a);
			else
				value.add(a/100);
		}

		System.out.println("------------------------------------------");
		System.err.println(value);
		System.out.println("------------------------------------------");

		System.out.println("Hi");
		HttpURLConnection conn = null;
		DataOutputStream os = null;
		try {
			URL url = new URL(addr + "/PredictionPortfolio/"); // important to add the trailing slash after add

			MappKeyValue gg = new MappKeyValue(k, value);

			System.out.println("gg - - - - " + gg);

			String[] inputData = { "{" + gg.toString().substring(0, gg.toString().length() - 2) + "}" };

			System.out.println("input data - " + inputData.toString());
			System.out.println("---------------------------------------------");
			for (String input : inputData) {
				System.out.println("input - " + input);

				byte[] postData = input.getBytes(StandardCharsets.UTF_8);
				System.out.println("postData - " + postData);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
				os = new DataOutputStream(conn.getOutputStream());
				os.write(postData);
				os.flush();

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				StringBuilder sb = new StringBuilder();

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					sb.append(output + "\n");
				}
				System.out.println("\n sb to string - \n" + sb.toString());

				try {
					org.json.JSONObject j = new org.json.JSONObject(sb.toString());
					org.json.JSONArray jArr = j.getJSONArray("result");
//				System.out.println(jArr.get(2).toString());
					System.out.println("json array - " + jArr);

					for (int i = 0; i < jArr.length(); i++) {
						Portfolio_Performance prtp = null;
						ObjectMapper om = new ObjectMapper();
						prtp = om.readValue((String) jArr.get(i).toString(), Portfolio_Performance.class);
						System.out.println("this is prtp - " + prtp);
						p.add(prtp);
					}

					System.out.println("this is p - " + p);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conn.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		System.out.println(p);
		return p;

	}
	
	public ArrayList<Object> recommendedPortfolio() {
		
		ArrayList<RecommendedPortPerformance> p = new ArrayList();
		ArrayList<Object> finalList = new ArrayList<>();



		System.out.println("Hi");
		HttpURLConnection conn = null;
		DataOutputStream os = null;
		try {
			URL url = new URL(addr + "/recommendedPortfolio/"); // important to add the trailing slash after add





				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		
		
		
				StringBuilder sb = new StringBuilder();

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					sb.append(output + "\n");
				}
				System.out.println("\n sb to string - \n" + sb.toString());

				try {
					org.json.JSONObject j = new org.json.JSONObject(sb.toString());
					org.json.JSONArray jArr = j.getJSONArray("metrics");
					org.json.JSONArray jArr2 = j.getJSONArray("weight");
//				System.out.println(jArr.get(2).toString());
					System.out.println("json array  metrics- " + jArr);
					System.out.println("json array weight - "+jArr2);

					for (int i = 0; i < jArr.length(); i++) {
						RecommendedPortPerformance prtp = null;
						ObjectMapper om = new ObjectMapper();
						prtp = om.readValue((String) jArr.get(i).toString(), RecommendedPortPerformance.class);
						System.out.println("this is prtp - " + prtp);
						p.add(prtp);
					}
					finalList.add(p);
					ArrayList<RecommendedAssetWeights> rawList = new ArrayList<>();
					for (int i = 0; i < jArr2.length(); i++) {
						RecommendedAssetWeights r = null;
						ObjectMapper om = new ObjectMapper();
						r = om.readValue((String) jArr2.get(i).toString(), RecommendedAssetWeights.class);
						System.out.println("this is r - " + r);
						rawList.add(r);
//						raw.addRecommendedAssetWeights(r.getAsset_id(), r.getPortfolio_id(), r.getWeight(), r.getAmount());
					}
					finalList.add(rawList);


					System.out.println("this is rawList - " + rawList);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conn.disconnect();
				
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		System.out.println(p);
		return finalList;
		
		
	}

}
