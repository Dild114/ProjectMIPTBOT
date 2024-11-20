package app;

import tgBot.parser.Article;
import tgBot.parser.ourParsers.ParserByVadim;

public class Main {
  public static void main(String[] args) {
    ParserByVadim parser = new ParserByVadim();
    for (Article i : parser.parseAllSite()) {
      System.out.println(i.title);
    }
  }
}
