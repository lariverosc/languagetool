/* LanguageTool, a natural language style checker 
 * Copyright (C) 2008 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.fr;

import junit.framework.TestCase;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.TestTools;
import org.languagetool.language.French;
import org.languagetool.rules.GenericUnpairedBracketsRule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;

public class GenericUnpairedBracketsRuleTest extends TestCase {

  private GenericUnpairedBracketsRule rule;
  private JLanguageTool langTool;
  
  public void testFrenchRule() throws IOException {
    setUpRule(new French());
    // correct sentences:
    assertMatches("(Qu'est ce que c'est ?)", 0);
    // incorrect sentences:
    assertMatches("(Qu'est ce que c'est ?", 1);
  }

  private void setUpRule(Language language) throws IOException {
    rule = new GenericUnpairedBracketsRule(TestTools.getEnglishMessages(), language);
    langTool = new JLanguageTool(language);
  }

  private void assertMatches(String input, int expectedMatches) throws IOException {
    final RuleMatch[] matches = rule.match(langTool.getAnalyzedSentence(input));
    assertEquals(expectedMatches, matches.length);
  }
}
