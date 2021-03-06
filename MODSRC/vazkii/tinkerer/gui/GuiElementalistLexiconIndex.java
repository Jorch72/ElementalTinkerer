/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Jan 2013
package vazkii.tinkerer.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.GuiReference;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchCategory;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;

/**
 * GuiElementalistLexiconIndex
 *
 * The Index Page of the Elementalist's Lexicon, used to look
 * trough researches.
 *
 * @author Vazkii
 */
public class GuiElementalistLexiconIndex extends GuiScreen {

	public static int currentPage = 0;
	public static byte currentSection = 0;

	ResearchNode[] currentShowingNodes;

    int xStart, yStart;

	@Override
	public void initGui() {
		super.initGui();
        xStart = (width - 146) / 2;
        yStart = (height - 180) / 2;
        buttonList.clear();
        updateButtons();
	}

	public void updateResearchNodes() {
		List<ResearchNode> workingNodes = new ArrayList();
		int foundNodesInCurrentCategory = 0;
        for(Short s : ResearchLibrary.allNodes.keySet()) {
        	if(workingNodes.size() >= GuiReference.LINES_IN_ELEMENTALIST_LEXICON * (currentPage + 1))
        		break;

        	ResearchNode node = ResearchLibrary.allNodes.get(s);
        	ResearchCategory category = ResearchLibrary.categories.get(currentSection);
        	if(category.hasNode(node)) {
        		foundNodesInCurrentCategory++;
        		// Check if this page happens to have this research node
        		if(foundNodesInCurrentCategory > currentPage * GuiReference.LINES_IN_ELEMENTALIST_LEXICON)
        			workingNodes.add(node);
        	}
        }

        currentShowingNodes = workingNodes.toArray(new ResearchNode[workingNodes.size()]);
	}

	public void updateButtons() {
		buttonList.clear();
		addBookmarkButtons();

		buttonList.add(new GuiInvisibleButton(6, xStart + 18, yStart + 158, 15, 12));
		buttonList.add(new GuiInvisibleButton(7, xStart + 115, yStart + 158, 15, 12));

		updateResearchNodes();
		addResearchButtons();

	}

	public void addBookmarkButtons() {
        buttonList.add(new GuiInvisibleButton(0, xStart + 136, yStart + 15, 41, 9));
        buttonList.add(new GuiInvisibleButton(1, xStart + 136, yStart + 29, 27, 9));
        buttonList.add(new GuiInvisibleButton(2, xStart + 136, yStart + 43, 31, 9));
        buttonList.add(new GuiInvisibleButton(3, xStart + 136, yStart + 57, 17, 9));
        buttonList.add(new GuiInvisibleButton(4, xStart + 136, yStart + 71, 31, 9));
        buttonList.add(new GuiInvisibleButton(5, xStart + 136, yStart + 85, 23, 9));
	}

	public void addResearchButtons() {
		for(int i = 0; i < currentShowingNodes.length; i++)
			buttonList.add(new GuiInvisibleButton(8 + i, xStart + 4, yStart + 16 + i * 12, 120, 9));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);

		if(par1GuiButton.id <= 5) {
			currentSection = (byte) par1GuiButton.id;
			currentPage = 0;
			updateButtons();
		} else if(par1GuiButton.id == 6) {
			if(currentPage != 0) {
				currentPage--;
				updateButtons();
			}
		} else if(par1GuiButton.id == 7) {
        	ResearchCategory category = ResearchLibrary.categories.get(currentSection);
			int nodesSize = category.getNodes().size();
			if(nodesSize > (currentPage + 1) * 12) {
				currentPage += 1;
				updateButtons();
			}
		} else {
			ResearchNode node = currentShowingNodes[par1GuiButton.id - 8];
			GuiElementalistLexiconResearch researchGui = new GuiElementalistLexiconResearch(node, currentSection);
			MiscHelper.getMc().displayGuiScreen(researchGui);
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(ResourcesReference.GUI_ELEMENTALIST_LEXICON_INDEX_TEXTURE);
        int shiftX = xStart + 137;
        int shiftY = yStart + 15;
        int bookmarkDifference = 14;

    	ResearchCategory category = ResearchLibrary.categories.get(currentSection);
		int nodesSize = category.getNodes().size();
		boolean prev = currentPage != 0;
		boolean next = nodesSize > (currentPage + 1) * 12;

        drawTexturedModalRect(xStart, yStart, 0, 0, 186, 180);

        if (prev) fontRenderer.drawString("<", xStart + 20, yStart + 161, 0);
		if (next) fontRenderer.drawString(">", xStart + 117, yStart + 161, 0);

		int total = ResearchLibrary.allNodes.size();
		int done = 0;
		int undone = 0;
		for(Short s : ResearchHelper.clientResearch.researchesDone.keySet()) {
			byte value = ResearchHelper.clientResearch.researchesDone.get(s);
			if(value == (byte) 2)
				done += 1;
			else if(value == (byte) 1)
				undone += 1;
		}

		drawCenteredString(fontRenderer, FormattingCode.BLUE + "Completed: " + FormattingCode.YELLOW +  done + FormattingCode.BLUE + "/" + FormattingCode.YELLOW + total, width / 2, yStart + 185, 0xFFFFFF);
		if(undone > 0)
			drawCenteredString(fontRenderer, FormattingCode.RED + "Uncompleted Researches: " + undone, width / 2, yStart + 196, 0xFFFFFF);

        fontRenderer.setUnicodeFlag(true); // Start the fancy font rendering
        fontRenderer.drawStringWithShadow((currentSection == 0 ? FormattingCode.UNDERLINE : "") + ResearchReference.CATEGORY_NAME_GENERAL, shiftX, shiftY, 0xFFFFFF);
        shiftY += bookmarkDifference;
        fontRenderer.drawStringWithShadow((currentSection == 1 ? FormattingCode.UNDERLINE : "") + ResearchReference.CATEGORY_NAME_PURE, shiftX, shiftY, 0xFFFFFF);
        int i = 2;
        for(Element element : Element.class.getEnumConstants()) {
            shiftY += bookmarkDifference;
            fontRenderer.drawStringWithShadow((currentSection == i ? FormattingCode.UNDERLINE : "") + element.getName(), shiftX, shiftY, 0xFFFFFF);
            ++i;
        }
        int i1 = 0;
        for(ResearchNode node : currentShowingNodes) {
        	boolean isHover = ((GuiInvisibleButton) buttonList.get(i1 + 8)).isHovered();
        	fontRenderer.drawString((isHover ? "* " : "") + (ResearchHelper.clientResearch.isResearchDone(node.index) ? node.displayName : FormattingCode.ITALICS + "Unknown Chapter"), xStart + 24, yStart + 16 + i1 * 12, 0);
        	GL11.glPushMatrix();
        	GL11.glScalef(0.5F, 0.5F, 0.5F);
        	GL11.glColor3f(0F, 0F, 0F);
            RenderHelper.renderResearchIcon(node, true, (xStart + 14) * 2 + 1, (yStart + 16 + i1 * 12) * 2 + 1, zLevel);
        	zLevel += 1;
            GL11.glColor3f(1F, 1F, 1F);
            RenderHelper.renderResearchIcon(node, true, (xStart + 14) * 2, (yStart + 16 + i1 * 12) * 2, zLevel);
            zLevel -= 1;
            GL11.glPopMatrix();

        	++i1;
        }
        fontRenderer.setUnicodeFlag(false); // End the fancy font rendering

		if(((GuiInvisibleButton) buttonList.get(6)).isHovered() && prev)
			RenderHelper.renderTooltip(par1, par2, "Prev. Page");
		if(((GuiInvisibleButton) buttonList.get(7)).isHovered() && next)
			RenderHelper.renderTooltip(par1, par2, "Next Page");

        GL11.glDisable(GL11.GL_BLEND);
		super.drawScreen(par1, par2, par3);
	}

}
