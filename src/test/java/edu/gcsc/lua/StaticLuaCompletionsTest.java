package edu.gcsc.lua;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.junit.Test;

import edu.gcsc.lua.LuaCompletionProvider;
import edu.gcsc.lua.StaticLuaCompletions;

public class StaticLuaCompletionsTest {

	@Test
	public void testAddBasicCompletion() {
		StaticLuaCompletions comps = new StaticLuaCompletions(null);
		List<Completion> completions = new ArrayList<Completion>();
		comps.addCompletion(completions, null, new String[] { "BC", "for",
				"for-loop", "<b>mighty for loop for loops" });
		assertEquals(1, completions.size());
		assertEquals("for", completions.get(0).getInputText());
		assertEquals("for-loop",
				((BasicCompletion) completions.get(0)).getShortDescription());
		assertEquals("<b>mighty for loop for loops", completions.get(0)
				.getSummary());

	}

	@Test
	public void testAddShortHandCompletion() {
		StaticLuaCompletions comps = new StaticLuaCompletions(null);
		List<Completion> completions = new ArrayList<Completion>();
		comps.addCompletion(completions, null, new String[] { "SH", "for",
				"for i=1,10 do end", "bigger for-loop" });
		assertEquals(1, completions.size());
		assertEquals("for", completions.get(0).getInputText());
		assertEquals("for i=1,10 do end", completions.get(0)
				.getReplacementText());
		assertEquals("bigger for-loop",
				((BasicCompletion) completions.get(0)).getShortDescription());
	}

	@Test
	public void testAddFunctionCompletion() {
		StaticLuaCompletions comps = new StaticLuaCompletions(null);
		List<Completion> completions = new ArrayList<Completion>();
		comps.addCompletion(completions,new LuaCompletionProvider(), new String[] { "PC", "test", "summary", "void", "a", "b", "c" });
		assertEquals(1, completions.size());
		FunctionCompletion fc = (FunctionCompletion) completions.get(0);
		assertEquals(3, fc.getParamCount());
		assertEquals("void", fc.getType());
		assertEquals("test", fc.getName());
		assertEquals(
				"<html><b>void test(a,b,c)</b><hr><br>summary<br><br><br><b>Parameters:</b><br><center><table width='90%'><tr><td><b>a</b>&nbsp;<br><b>b</b>&nbsp;<br><b>c</b>&nbsp;<br></td></tr></table></center><br><br>",
				fc.getSummary());
	}

	@Test
	public void testStaticTableImport() {
		StaticLuaCompletions comps = new StaticLuaCompletions(null);
		List<Completion> completions = new ArrayList<Completion>();
		comps.addCompletions(completions, new LuaCompletionProvider(), comps.completionsTable);
		assertEquals(72, completions.size());
	}
}
