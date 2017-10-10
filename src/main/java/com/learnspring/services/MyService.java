package com.learnspring.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnspring.bean.MyBean;
import com.learnspring.dao.DatabaseChangeLogDao;
import com.learnspring.model.Databasechangelog;
import com.learnspring.model.DatabasechangelogPK;
import com.learnspring.model.Tag;
import com.learnspring.util.JsonUtil;

@Service
@Transactional
public class MyService {

	@Autowired
	MyBean mybean;

	@Autowired
	private Environment environment;

	@Autowired
	private DatabaseChangeLogDao changeLogDao;

	@Autowired
	private DocumentService documentService;

	public String getDatabaseChangeLogDetails() throws JsonProcessingException {
		mybean.getBeanName();
		List<Databasechangelog> databaseChangeLogDetails = changeLogDao.getDatabaseChangeLogDetails();
		String jsonForm = JsonUtil.getJsonForm(databaseChangeLogDetails);
		return jsonForm;

	}

	public void getChangeLogDetails(DatabasechangelogPK pk) {
		Databasechangelog changeLogDetails = changeLogDao.getChangeLogDetails(pk);
	}

	public String readChangeSetInformation(DatabasechangelogPK pk) throws Exception {
		String baseProjectLocation = "C:/Users/pritam.panhale/workspace/testMaven/";
		String tag = documentService.getTag(baseProjectLocation + pk.getFilename(), pk.getId(), pk.getAuthor());
		return tag;
	}

	public void updateTag(Tag tag) {
		changeLogDao.setTag(tag);
	}

	public boolean checkDirectory(String projectDirectory) {
		File file = new File(projectDirectory + "/.git");
		boolean val = false;
		if (file.exists() && file.isDirectory()) {
			val = true;
		}
		return val;
	}

	public List<String> getBranches(String projectDirectory) throws GitAPIException, IOException {
		List<String> listOfBranches = null;
		File file = new File(projectDirectory + "/.git");
		boolean val = false;
		if (file.exists() && file.isDirectory()) {

			Repository repo = new FileRepositoryBuilder().setGitDir(file).build();
			List<Ref> call = new Git(repo).branchList().setListMode(ListMode.REMOTE).call();
			listOfBranches = new ArrayList<String>();

			for (Ref ref : call) {
				listOfBranches.add(ref.getName());
			}

		}
		return listOfBranches;
	}
	
	public String pullResource(String baseProjectDir , String branchName) throws Exception{

		Repository repo = new FileRepositoryBuilder().setGitDir(new File(baseProjectDir +"/.git"))
				.build();
		
		Git git = new Git(repo);
		String[] split = branchName.split("/");
		System.out.println("Branch: " + split[split.length-1]);
		git.checkout().setName(split[split.length-1]).call();
		
		PullCommand pull = git.pull();
		PullResult call = pull.call();
		
		System.out.println(call);
		return call.toString();
	
	}
}
