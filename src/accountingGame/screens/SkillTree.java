package accountingGame.screens;

import java.util.ArrayList;

import accountingGame.Skill;

public class SkillTree {
	
	private ArrayList<Skill> skillTree = new ArrayList();
	
	Skill classifyALE;
	Skill accountingEquation;
	
	public SkillTree()
	{
		skillTree.add(assetsLiabilitiesOrEquitySkill());
		skillTree.add(accountingEquationSkill(classifyALE.getCurrentLevel(),classifyALE.getMaxLevel()));
	}
	
	public ArrayList<Skill> getSkillTree() {
		return skillTree;
	}

	public void setSkillTree(ArrayList<Skill> skillTree) {
		this.skillTree = skillTree;
	}

	private Skill assetsLiabilitiesOrEquitySkill() {
		classifyALE = new Skill();
		classifyALE.setSkillName("Assets/Liabilites/Equity Classification");
		classifyALE.setUnlocked(true);
		classifyALE.setMaxLevel(5);
		return classifyALE;
	}
	
	private Skill accountingEquationSkill(int preReqSkillLevel, int maxSkillLevel)
	{
		accountingEquation = new Skill();
		accountingEquation.setSkillName("A=L+E");
		accountingEquation.setUnlocked(checkPreReq(preReqSkillLevel,maxSkillLevel));
		accountingEquation.setMaxLevel(5);
		return accountingEquation;
	}
	
	private boolean checkPreReq(int preReqSkillLevel, int maxSkillLevel)
	{
		if (preReqSkillLevel>= (maxSkillLevel/2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
