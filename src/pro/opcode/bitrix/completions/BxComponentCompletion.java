package pro.opcode.bitrix.completions;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import pro.opcode.bitrix.BxReferencePatterns;
import pro.opcode.bitrix.api.BxCore;

import java.util.Arrays;
import java.util.List;

public class BxComponentCompletion extends CompletionContributor
{
	/* Тут перечислены компоненты для автокомплита в случае, если проект
	 * не содержит папки с компонентами bitrix */
	private static List<String> bxComponents = Arrays.asList(
		"advertising.banner",
		"bitrixcloud.mobile.monitoring.detail",
		"bitrixcloud.mobile.monitoring.edit",
		"bitrixcloud.mobile.monitoring.list",
		"bitrixcloud.mobile.monitoring.push",
		"bizproc.document",
		"bizproc.document.history",
		"bizproc.log",
		"bizproc.task",
		"bizproc.task.list",
		"bizproc.wizards",
		"bizproc.wizards.index",
		"bizproc.wizards.list",
		"bizproc.wizards.log",
		"bizproc.wizards.new",
		"bizproc.wizards.setvar",
		"bizproc.wizards.start",
		"bizproc.wizards.task",
		"bizproc.wizards.view",
		"bizproc.workflow.edit",
		"bizproc.workflow.info",
		"bizproc.workflow.list",
		"bizproc.workflow.setvar",
		"bizproc.workflow.start",
		"blog",
		"blog.blog",
		"blog.blog.draft",
		"blog.blog.edit",
		"blog.blog.favorite",
		"blog.blog.moderation",
		"blog.calendar",
		"blog.category",
		"blog.commented_posts",
		"blog.friends",
		"blog.group.blog",
		"blog.groups",
		"blog.info",
		"blog.menu",
		"blog.metaweblog",
		"blog.new_blogs",
		"blog.new_comments",
		"blog.new_posts",
		"blog.new_posts.list",
		"blog.popular_blogs",
		"blog.popular_posts",
		"blog.post",
		"blog.post.comment",
		"blog.post.edit",
		"blog.post.trackback",
		"blog.post.trackback.get",
		"blog.rss",
		"blog.rss.all",
		"blog.rss.link",
		"blog.search",
		"blog.user",
		"blog.user.group",
		"blog.user.settings",
		"blog.user.settings.edit",
		"breadcrumb",
		"calendar.events.list",
		"calendar.grid",
		"calendar.livefeed.edit",
		"calendar.livefeed.view",
		"catalog",
		"catalog.brandblock",
		"catalog.comments",
		"catalog.compare.list",
		"catalog.compare.result",
		"catalog.element",
		"catalog.filter",
		"catalog.link.list",
		"catalog.main",
		"catalog.search",
		"catalog.section",
		"catalog.section.list",
		"catalog.sections.top",
		"catalog.set.constructor",
		"catalog.smart.filter",
		"catalog.socnets.buttons",
		"catalog.tabs",
		"catalog.top",
		"controller.shared.add",
		"controller.site.control",
		"controller.site.list",
		"currency.rates",
		"desktop",
		"event_list",
		"fileman.light_editor",
		"form",
		"form.result.edit",
		"form.result.list",
		"form.result.list.my",
		"form.result.new",
		"form.result.view",
		"forum",
		"forum.comments",
		"forum.help",
		"forum.index",
		"forum.interface",
		"forum.message.approve",
		"forum.message.move",
		"forum.message.send",
		"forum.message.template",
		"forum.pm.edit",
		"forum.pm.folder",
		"forum.pm.list",
		"forum.pm.read",
		"forum.pm.search",
		"forum.post_form",
		"forum.rss",
		"forum.rules",
		"forum.search",
		"forum.statistic",
		"forum.subscribe.list",
		"forum.topic.active",
		"forum.topic.last",
		"forum.topic.list",
		"forum.topic.move",
		"forum.topic.new",
		"forum.topic.read",
		"forum.topic.reviews",
		"forum.topic.search",
		"forum.user.list",
		"forum.user.post",
		"forum.user.profile.edit",
		"forum.user.profile.view",
		"furniture.catalog.index",
		"furniture.catalog.random",
		"furniture.vacancies",
		"highloadblock.list",
		"highloadblock.view",
		"iblock.button.element",
		"iblock.button.element.add",
		"iblock.button.element.search",
		"iblock.element.add",
		"iblock.element.add.form",
		"iblock.element.add.list",
		"iblock.tv",
		"iblock.vote",
		"iblock.wizard",
		"idea",
		"idea.category.list",
		"idea.comment.list",
		"idea.detail",
		"idea.edit",
		"idea.filter",
		"idea.list",
		"idea.popup",
		"idea.rss",
		"idea.search",
		"idea.statistic",
		"idea.subscribe",
		"idea.tags",
		"im.messenger",
		"infoportal.element.add.form",
		"learning.chapter.detail",
		"learning.course",
		"learning.course.contents",
		"learning.course.detail",
		"learning.course.list",
		"learning.course.tree",
		"learning.lesson.detail",
		"learning.search",
		"learning.student.certificates",
		"learning.student.gradebook",
		"learning.student.profile",
		"learning.student.transcript",
		"learning.test",
		"learning.test.list",
		"learning.test.self",
		"lists",
		"lists.element.edit",
		"lists.element.navchain",
		"lists.field.edit",
		"lists.fields",
		"lists.file",
		"lists.list",
		"lists.list.edit",
		"lists.lists",
		"lists.menu",
		"lists.sections",
		"main.calendar",
		"main.clock",
		"main.colorpicker",
		"main.feedback",
		"main.file.input",
		"main.include",
		"main.informer",
		"main.interface.filter",
		"main.interface.form",
		"main.interface.grid",
		"main.interface.toolbar",
		"main.lookup.input",
		"main.map",
		"main.post.form",
		"main.post.list",
		"main.profile",
		"main.register",
		"main.share",
		"main.site.selector",
		"main.tree.selector",
		"main.user.link",
		"map.google.search",
		"map.google.system",
		"map.google.view",
		"map.yandex.search",
		"map.yandex.system",
		"map.yandex.view",
		"menu",
		"menu.sections",
		"mobileapp.auth",
		"mobileapp.edit",
		"mobileapp.filter",
		"mobileapp.interface.checkboxes",
		"mobileapp.interface.radiobuttons",
		"mobileapp.interface.topswitchers",
		"mobileapp.list",
		"mobileapp.list.enclosed",
		"mobileapp.menu",
		"mobileapp.push",
		"news",
		"news.calendar",
		"news.detail",
		"news.index",
		"news.line",
		"news.list",
		"photo",
		"photo.detail",
		"photogallery",
		"photogallery.detail",
		"photogallery.detail.comment",
		"photogallery.detail.edit",
		"photogallery.detail.list",
		"photogallery.detail.list.ex",
		"photogallery.gallery.edit",
		"photogallery.gallery.list",
		"photogallery.imagerotator",
		"photogallery.interface",
		"photogallery.section",
		"photogallery.section.edit",
		"photogallery.section.edit.icon",
		"photogallery.section.list",
		"photogallery.upload",
		"photogallery_user",
		"photogallery.user",
		"photo.random",
		"photo.section",
		"photo.sections.top",
		"player",
		"pull.request",
		"rating.result",
		"rating.vote",
		"rss.out",
		"rss.show",
		"search.form",
		"search.page",
		"search.suggest.input",
		"search.tags.cloud",
		"search.tags.input",
		"search.title",
		"socialnetwork",
		"socialnetwork.activity",
		"socialnetwork.admin.set",
		"socialnetwork.bizproc",
		"socialnetwork.bizproc_edit",
		"socialnetwork.blog.blog",
		"socialnetwork.blog.draft",
		"socialnetwork.blog.menu",
		"socialnetwork.blog.moderation",
		"socialnetwork.blog.post",
		"socialnetwork.blog.post.comment",
		"socialnetwork.blog.post.edit",
		"socialnetwork.blog.rss",
		"socialnetwork.events",
		"socialnetwork.events_dyn",
		"socialnetwork.features",
		"socialnetwork.forum.post_form",
		"socialnetwork.forum.topic.last",
		"socialnetwork.forum.topic.list",
		"socialnetwork.forum.topic.new",
		"socialnetwork.forum.topic.read",
		"socialnetwork_group",
		"socialnetwork.group",
		"socialnetwork.group_ban",
		"socialnetwork.group_create",
		"socialnetwork.group_create.ex",
		"socialnetwork.group_delete",
		"socialnetwork.group.iframe.popup",
		"socialnetwork.group_list",
		"socialnetwork.group_menu",
		"socialnetwork.group_mods",
		"socialnetwork.group_requests",
		"socialnetwork.group_request_search",
		"socialnetwork.group_requests.ex",
		"socialnetwork.group_requests_out",
		"socialnetwork.group_request_user",
		"socialnetwork.group_search",
		"socialnetwork.group.selector",
		"socialnetwork.group_top",
		"socialnetwork.group_users",
		"socialnetwork.group_users.ex",
		"socialnetwork.log.entry",
		"socialnetwork.log.ex",
		"socialnetwork.log.filter",
		"socialnetwork.log.rss",
		"socialnetwork.log.rss.link",
		"socialnetwork.menu",
		"socialnetwork.message_form",
		"socialnetwork.messages_chat",
		"socialnetwork.messages_input",
		"socialnetwork.messages_menu",
		"socialnetwork.messages_output",
		"socialnetwork.messages_requests",
		"socialnetwork.messages_users",
		"socialnetwork.messages_users_messages",
		"socialnetwork.reindex",
		"socialnetwork.subscribe",
		"socialnetwork.subscribe_list",
		"socialnetwork_user",
		"socialnetwork.user_ban",
		"socialnetwork.user_birthday",
		"socialnetwork.user_friends",
		"socialnetwork.user_friends_add",
		"socialnetwork.user_friends_delete",
		"socialnetwork.user_friends.ex",
		"socialnetwork.user_groups",
		"socialnetwork.user_groups.link.add",
		"socialnetwork.user_groups.search_form",
		"socialnetwork.user_leave_group",
		"socialnetwork.user_menu",
		"socialnetwork.user_profile",
		"socialnetwork.user_profile_edit",
		"socialnetwork.user_request_group",
		"socialnetwork.user_requests.ex",
		"socialnetwork.user_search",
		"socialnetwork.user_search_input",
		"socialnetwork.user_settings_edit",
		"socserv.auth.form",
		"socserv.auth.split",
		"statistic.table",
		"subscribe.edit",
		"subscribe.form",
		"subscribe.index",
		"subscribe.news",
		"subscribe.simple",
		"support.faq",
		"support.faq.element.detail",
		"support.faq.element.list",
		"support.faq.section.list",
		"support.ticket",
		"support.ticket.edit",
		"support.ticket.list",
		"support.wizard",
		"system.auth.authorize",
		"system.auth.changepasswd",
		"system.auth.confirmation",
		"system.auth.forgotpasswd",
		"system.auth.form",
		"system.auth.initialize",
		"system.auth.registration",
		"system.field.edit",
		"system.field.view",
		"system.pagenavigation",
		"system.show_message",
		"voting.current",
		"voting.form",
		"voting.list",
		"voting.result",
		"voting.vote.edit",
		"webservice.checkauth",
		"webservice.server",
		"webservice.statistic",
		"wiki",
		"wiki.categories",
		"wiki.category",
		"wiki.discussion",
		"wiki.edit",
		"wiki.history",
		"wiki.history.diff",
		"wiki.menu",
		"wiki.show"
	);

	/**
	 * Построение автокомплита компонента в процессе набора $APPLICATION->IncludeComponent(...)
	 */
	public BxComponentCompletion() {
		extend(CompletionType.BASIC, BxReferencePatterns.bxComponentReference(), new CompletionProvider<CompletionParameters>() {
			public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {
				BxCore bitrix = new BxCore(parameters.getPosition().getProject());

				if (!bitrix.isComponentsFolderExists())
					for (String component : bxComponents)
						resultSet.addElement(LookupElementBuilder.create("bitrix:" + component));

				for (VirtualFile component : bitrix.getComponents())
					resultSet.addElement(LookupElementBuilder.create(String.format("%s:%s",
						component.getParent().getName(),
						component.getName()
					)));
			}
		});
	}
}
